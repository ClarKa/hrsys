package org.hrsys.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.hrsys.dao.DepartmentManager;
import org.hrsys.dao.EmployeeManager;
import org.hrsys.dto.EmployeeDTO;
import org.hrsys.facades.EmployeeInfoFacade;
import org.hrsys.constants.CommonConstants;
import org.hrsys.constants.ServicePaths;

@RestController
@RequestMapping(value = ServicePaths.GET_EMPLOYEE_PATH)
public class EmployeeInfoServices {
    private EmployeeInfoFacade employeeInfoFacade = new EmployeeInfoFacade();
    
    @Autowired
    EmployeeManager employeeManager;

    @Autowired
    DepartmentManager departmentManager;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize(CommonConstants.HAS_ROLE_ADMIN)
    public List<EmployeeDTO> getAllEmployees() {
        return employeeInfoFacade.getAllEmployees(employeeManager);
    }

    @RequestMapping(value = ServicePaths.GET_ONE_EMPLOYEE_PATH + "/{employeeid}", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize(CommonConstants.HAS_ROLE_AUTHENTICATED)
    public EmployeeDTO getOneEmployee(@PathVariable("employeeid") int employeeid) {
        return employeeInfoFacade.getOneEmployee(employeeid, employeeManager);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @PreAuthorize(CommonConstants.HAS_ROLE_ADMIN)
    public EmployeeDTO createEmployee(EmployeeDTO employeeDto) {
        return employeeInfoFacade.createEmployee(employeeDto, employeeManager);
    }
    
    @RequestMapping(value = ServicePaths.GET_ONE_EMPLOYEE_PATH + "/{employeeid}", method = RequestMethod.DELETE, produces = "application/json")
    @PreAuthorize(CommonConstants.HAS_ROLE_ADMIN)
    public EmployeeDTO deleteOneEmployee(@PathVariable("employeeid") int employeeID) {
        return employeeInfoFacade.deleteOneEmployee(employeeID, employeeManager);
    }
    
    @RequestMapping(value = ServicePaths.GET_ONE_EMPLOYEE_PATH + "/{employeeid}", method = RequestMethod.POST, produces = "application/json")
    @PreAuthorize(CommonConstants.HAS_ROLE_ADMIN)
    public EmployeeDTO updateOneEmployee(EmployeeDTO employeeDto, @PathVariable("employeeid") int employeeID) {
        return employeeInfoFacade.updateOneEmployee(employeeID, employeeDto, employeeManager);
    }
}
