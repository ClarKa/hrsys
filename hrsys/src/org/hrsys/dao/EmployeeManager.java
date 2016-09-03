package org.hrsys.dao;

import org.hrsys.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeManager {
    public List<Employee> getAllEmployee();
    public List<Employee> getAvailableEmployee();
    public void createEmployee(Employee emp) throws SQLException;
    public Employee getOneEmployee(int employeeID); 
}