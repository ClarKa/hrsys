package org.hrsys.webservices;

import java.util.HashMap;

import org.hrsys.constants.ServicePaths;
import org.hrsys.dao.BankManager;
import org.hrsys.dao.PaychecksManager;
import org.hrsys.dto.PaychecksDTO;
import org.hrsys.facades.PaychecksFacade;
import org.hrsys.helpers.MetaAnnotations.EmployeeIdMatch;
import org.hrsys.helpers.MetaAnnotations.EmployeeIdMatchOrIsAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ServicePaths.GET_PAYCHECKS_PATH)
public class PaychecksServices {
    @Autowired
    PaychecksManager paychecksManager;
    
    @Autowired
    BankManager bankManager;
    
    PaychecksFacade paychecksFacade = new PaychecksFacade();
    
    @RequestMapping(value = "/{employeeid}", method = RequestMethod.GET, produces = "application/json")
    @EmployeeIdMatchOrIsAdmin
    public PaychecksDTO getPaychecksForEmployee(@PathVariable("employeeid") int employeeID) {
        return paychecksFacade.getPaychecksForEmployee(employeeID, paychecksManager);
    }
    
    @RequestMapping(value = "/{employeeid}", method = RequestMethod.PUT, produces = "application/json")
    @EmployeeIdMatch
    public PaychecksDTO updatePaychecksForEmployee(@PathVariable("employeeid") int employeeID, @RequestParam HashMap<String, String> map) {
        return paychecksFacade.updatePaychecksForEmployee(employeeID, map, paychecksManager, bankManager);
    }
}
