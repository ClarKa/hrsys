package org.hrsys.webservices;

import java.util.List;

import org.hrsys.constants.ServicePaths;
import org.hrsys.dao.BankManager;
import org.hrsys.dto.BankDTO;
import org.hrsys.facades.BankFacade;
import org.hrsys.helpers.MetaAnnotations.EmployeeIdMatchOrIsAdmin;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<BankDTO> getEmployeeBanks(@PathVariable("employeeid") int employeeId) {
        return bankFacade.getEmployeeBanks(employeeId, bankManager);
    }
}
