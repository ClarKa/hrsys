package org.hrsys.dto;

import org.hrsys.entity.Employee;

public class EmployeeDTO {
    private int employeeID;
    private String firstname;
    private String lastname;
    private String email;
    private int departmentID;
    private String error;
    
    public EmployeeDTO() {
        
    }
    
    public EmployeeDTO(Employee employee) {
        if (employee == null) {
            noteEmployeeNotExist();
            return;
        }
        this.firstname = employee.getFirstname();
        this.lastname = employee.getLastname();
        this.email = employee.getEmail();
        this.departmentID = employee.getDepartment().getDepartmentID();
    }
    
    private void noteEmployeeNotExist() {
        // TODO Auto-generated method stub
        error = "Employee does not exist.";
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstname() { 
        return firstname;
    }
     
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public String getLastname() {
        return lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
