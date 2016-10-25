package org.hrsys.facades;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
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

    public List<EmployeeDTO> getAllEmployees(EmployeeManager employeeManager) {
        List<Employee> employees= employeeManager.getAllEmployee();
        List<EmployeeDTO> employeesDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesDto.add(new EmployeeDTO(employee));
        }
        return employeesDto;
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDto, EmployeeManager employeeManager) {
        Employee employee = setEmployeeFromEmployeeDto(employeeDto);

        try {
            employeeManager.createEmployee(employee);
        } catch (Exception e) {
            employeeDto.setError(ExceptionUtils.getRootCause(e).getLocalizedMessage());
            return employeeDto;
        }
        return employeeDto;
    }

    public EmployeeDTO deleteOneEmployee(int employeeID, EmployeeManager employeeManager) {
        Employee employee;
        EmployeeDTO employeeDto;

        try {
            employee = employeeManager.deleteOneEmployee(employeeID);
        } catch (Exception e) {
            employeeDto = new EmployeeDTO();
            employeeDto.setError(ExceptionUtils.getRootCause(e).getLocalizedMessage());
            return employeeDto;
        }

        employeeDto = new EmployeeDTO(employee);
        return employeeDto;

    }

    public EmployeeDTO updateOneEmployee(int employeeID, EmployeeDTO employeeDto, EmployeeManager employeeManager) {
        Employee employee = setEmployeeFromEmployeeDto(employeeDto);
        employee.setEmployeeID(employeeID);

        try {
            employeeManager.updateOneEmployee(employeeID, employee);
        } catch (Exception e) {
            employeeDto.setError(ExceptionUtils.getRootCause(e).getLocalizedMessage());
        }

        return employeeDto;
    }
    
    private Employee setEmployeeFromEmployeeDto(EmployeeDTO employeeDto) {
        Employee employee = new Employee();
        employee.setFirstname(employeeDto.getFirstname());
        employee.setLastname(employeeDto.getLastname());
        employee.setGender(employeeDto.getGender());
        employee.setBirth(employeeDto.getBirth());
        employee.setSsn(employeeDto.getSsn());
        employee.setMarriage(employeeDto.getMarriage());
        employee.setNationality(employeeDto.getNationality());
        employee.setEducation(employeeDto.getEducation());
        employee.setEnrollmentDate(employeeDto.getEnrollmentDate());
        employee.setEmail(employeeDto.getEmail());
        employee.setPosition(employeeDto.getPosition());
        employee.setPhone(employeeDto.getPhone());
        employee.setAddress(employeeDto.getAddress());
        employee.setComment(employeeDto.getComment());
        
        Department department = new Department();
        department.setDepartmentID(employeeDto.getDepartmentID());
        employee.setDepartment(department);
        
        return employee;
    }
}
