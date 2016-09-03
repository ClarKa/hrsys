package org.hrsys.facades;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.transaction.Transactional;

import org.hrsys.dao.EmployeeManager;
import org.hrsys.dto.EmployeeDTO;
import org.hrsys.entity.Department;
import org.hrsys.entity.Employee;

public class EmployeeInfoFacade {
    
    public EmployeeDTO getOneEmployee(int employeeID, EmployeeManager employeeManager) {
        Employee employee = employeeManager.getOneEmployee(employeeID);
        EmployeeDTO employeeDto = new EmployeeDTO(employee);
        return employeeDto;
    }
    
    public List<Employee> getAllEmployees(EmployeeManager employeeManager) {
        List<Employee> employees= employeeManager.getAllEmployee();
        return employees;
    }
    
    public EmployeeDTO createEmployee(EmployeeDTO employeeDto, EmployeeManager employeeManager) {
        Employee employee = new Employee();
        employee.setFirstname(employeeDto.getFirstname());
        employee.setLastname(employeeDto.getLastname());
        employee.setEmail(employeeDto.getEmail());
        Department department = new Department();
        department.setDepartmentID(employeeDto.getDepartmentID());
        employee.setDepartment(department);
        
        try {
            employeeManager.createEmployee(employee);
        } catch (Exception e) {
            employeeDto.setError(e.toString());
        }
        return employeeDto;
    }
}
