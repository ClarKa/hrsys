package org.hrsys.dao;

import org.hrsys.entity.*;
import java.util.List;
import java.sql.SQLException;

public interface DepartmentManager {
    public List<Department> getAllDepartment();
    public List<Employee> getEmployeeForOneDepartment(int departmentId) throws SQLException;
    public void createDepartment(Department department, String[] employeeList) throws SQLException;
    public void deleteDepartment(Department department) throws SQLException;
    public void updateDepartment(Department department) throws SQLException;
}