package org.hrsys.webservices;

import java.util.List;

import javax.validation.Valid;

import org.hrsys.constants.ServicePaths;
import org.hrsys.dao.BankManager;
import org.hrsys.dto.BankDTO;
import org.hrsys.facades.BankFacade;
import org.hrsys.helpers.MetaAnnotations.EmployeeIdMatch;
import org.hrsys.helpers.MetaAnnotations.EmployeeIdMatchOrIsAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ServicePaths.GET_BANK_INFO_PATH)
public class BankServices {
    @Autowired
    BankManager bankManager;
    
    private BankFacade bankFacade = new BankFacade();
    
    @RequestMapping(value = "/{employeeid}", method = RequestMethod.GET, produces = "application/json")
    @EmployeeIdMatchOrIsAdmin
    public List<BankDTO> getEmployeeBanks(@PathVariable("employeeid") int employeeID) {
        return bankFacade.getEmployeeBanks(employeeID, bankManager);
    }
    
    @RequestMapping(value = "/{employeeid}", method = RequestMethod.POST, produces = "application/json")
    @EmployeeIdMatch
    public BankDTO createBankForEmployee(@Valid BankDTO bankDTO, BindingResult result, @PathVariable("employeeid") int employeeID) {
        if (result.hasErrors()) {
            bankDTO.setError(result.getAllErrors().get(0).getDefaultMessage());
            return bankDTO;
        }
        return bankFacade.createBankForEmployee(employeeID, bankDTO, bankManager);
    }
}
