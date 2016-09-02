package org.hrsys.webservices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.hrsys.dao.DepartmentManager;
import org.hrsys.dao.EmployeeManager;
import org.hrsys.dto.EmployeeDTO;
import org.hrsys.entity.Employee;
import org.hrsys.constants.ServicePaths;

@RestController
@RequestMapping(value = ServicePaths.GET_EMPLOYEE_PATH)
public class EmployeeInfoServices {
    @Autowired
    EmployeeManager employeeManager;

    @Autowired
    DepartmentManager departmentManager;
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Employee> getAllEmployees() {
	List<Employee> employees= new ArrayList<>();
	employees = employeeManager.getAllEmployee();
	return employees;
    }
    
    @RequestMapping(value = ServicePaths.GET_ONE_EMPLOYEE_PATH + "/{employeeid}", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize("isAuthenticated()")
    public Employee getOneEmployee() {
	Employee employee= new Employee();
	return employee;
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public void createEmployee(@RequestBody EmployeeDTO employeeDto) {
	System.out.println(employeeDto.getFirstname());
	System.out.println(employeeDto.getLastname());
	System.out.println(employeeDto.getEmail());
    }
}
