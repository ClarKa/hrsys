package org.hrsys.facades;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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

    public List<EmployeeDTO> getEmployees(EmployeeManager employeeManager, HashMap<String, Object> filters) throws ParseException {
        List<Employee> employees;
        if (!filters.isEmpty()) {
            for (Entry<String, Object> entry : filters.entrySet()) {
                String key = entry.getKey();
                
                // work with bootstrap data table;
                if (key.equals("_")) {
                    filters.remove(key);
                } else if (key == null || key.matches("[^a-zA-Z]")) {
                    return null;
                }
                
                if (key.equals("birth") || key.equals("enrollmentDate")) {
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    java.util.Date date = dateFormat.parse(entry.getValue().toString());
                    Date sqlDate = new Date(date.getTime());
                    entry.setValue(sqlDate);
                }
            }
            
            try {
                employees = employeeManager.getFiltered(filters);
            } catch (Exception e) {
                return null;
            }
        } else {
            employees = employeeManager.getAllEmployee();
        }
        
        List<EmployeeDTO> employeeDtos = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDtos.add(new EmployeeDTO(employee));
        }
        return employeeDtos;
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
        EmployeeDTO employeeDto = new EmployeeDTO();

        try {
            employee = employeeManager.deleteOneEmployee(employeeID);
        } catch (SQLException e) {
            employeeDto.setError(e.getLocalizedMessage());
            return employeeDto;
        } catch (Exception e) {
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
        } catch (SQLException e) {
            employeeDto.setError(e.getLocalizedMessage());
            return employeeDto;
        }
        catch (Exception e) {
            employeeDto.setError(ExceptionUtils.getRootCause(e).getLocalizedMessage());
            return employeeDto;
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
