package org.hrsys.webservices;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.hrsys.dao.DepartmentManager;
import org.hrsys.dao.EmployeeManager;
import org.hrsys.dto.EmployeeDTO;
import org.hrsys.facades.EmployeeInfoFacade;
import org.hrsys.helpers.MetaAnnotations.EmployeeIdMatchOrIsAdmin;
import org.hrsys.helpers.MetaAnnotations.IsAdmin;
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
    @IsAdmin
    public List<EmployeeDTO> getAllEmployees() {
        return employeeInfoFacade.getAllEmployees(employeeManager);
    }

    @RequestMapping(value = ServicePaths.GET_ONE_EMPLOYEE_PATH + "/{employeeid}", method = RequestMethod.GET, produces = "application/json")
    @EmployeeIdMatchOrIsAdmin
    public EmployeeDTO getOneEmployee(@PathVariable("employeeid") int employeeID) {
        return employeeInfoFacade.getOneEmployee(employeeID, employeeManager);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @IsAdmin
    public EmployeeDTO createEmployee(@Valid EmployeeDTO employeeDto) {
        return employeeInfoFacade.createEmployee(employeeDto, employeeManager);
    }
    
    @RequestMapping(value = ServicePaths.GET_ONE_EMPLOYEE_PATH + "/{employeeid}", method = RequestMethod.DELETE, produces = "application/json")
    @IsAdmin
    public EmployeeDTO deleteOneEmployee(@PathVariable("employeeid") int employeeID) {
        return employeeInfoFacade.deleteOneEmployee(employeeID, employeeManager);
    }
    
    @RequestMapping(value = ServicePaths.GET_ONE_EMPLOYEE_PATH + "/{employeeid}", method = RequestMethod.POST, produces = "application/json")
    @IsAdmin
    public EmployeeDTO updateOneEmployee(@Valid EmployeeDTO employeeDto, BindingResult result, @PathVariable("employeeid") int employeeID) {
        if (result.hasErrors()) {
            employeeDto.setError(result.getAllErrors().get(0).getDefaultMessage());
            return employeeDto;
        }
        return employeeInfoFacade.updateOneEmployee(employeeID, employeeDto, employeeManager);
    }
}
