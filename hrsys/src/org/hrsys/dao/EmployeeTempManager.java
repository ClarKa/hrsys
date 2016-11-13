package org.hrsys.dao;

import java.sql.SQLException;
import java.util.List;

import org.hrsys.entity.Employee;
import org.hrsys.entity.EmployeeTemp;

public interface EmployeeTempManager {
    public List<EmployeeTemp> getAllTempEmployee();
    public void createTempEmployee(EmployeeTemp emp) throws SQLException;
    public EmployeeTemp getOneRequest(int employeeID, boolean fetchEmployee); 
    public EmployeeTemp deleteOneRequest(int employeeID) throws SQLException;
    public void updateOneRequest(int employeeID, Employee employee) throws SQLException;
}
