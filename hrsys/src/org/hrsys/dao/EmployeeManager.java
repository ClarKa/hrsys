package org.hrsys.dao;

import org.hrsys.entity.Employee;
import java.util.List;
import java.sql.SQLException;

public interface EmployeeManager {
    public List<Employee> getAllEmployee();
    public List<Employee> getAvailableEmployee();
    public void createEmployee(Employee emp);
}