package org.hrsys.dto;

public class EmployeeDTO {
    private String firstname;
    private String lastname;
    private String email;
    
    public String getFirstname() { 
	return firstname;
    }
    
    public String getLastname() {
	return lastname;
    }
    
    public String getEmail() {
	return email;
    }
     
    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }
    
    public void setLastname(String lastname) {
	this.lastname = lastname;
    }
    
    public void setEmail(String email) {
	this.email = email;
    }
}
