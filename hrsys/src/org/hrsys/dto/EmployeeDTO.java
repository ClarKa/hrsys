package org.hrsys.dto;

import java.sql.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hrsys.constants.ValidationConstants;
import org.hrsys.entity.Employee;

public class EmployeeDTO {
    private int employeeID;
    
    @Pattern(regexp=ValidationConstants.PURE_WORD, message=ValidationConstants.INVALID_EMPLOYEE_NAME)
    private String firstname;
    
    @Pattern(regexp=ValidationConstants.PURE_WORD, message=ValidationConstants.INVALID_EMPLOYEE_NAME)
    private String lastname;
    
    @Pattern(regexp=ValidationConstants.GENDER, message=ValidationConstants.INVALID_GENDER)
    private String gender;
    
    @Email(message=ValidationConstants.INVALID_EMAIL)
    private String email;
    private int departmentID;
    private String departmentName;
    private Date birth;
    private String ssn;
    private String marriage;
    private String nationality;
    private String education;
    private Date enrollmentDate;
    
    @Pattern(regexp=ValidationConstants.PURE_WORD, message=ValidationConstants.INVALID_POSITION)
    private String position;
    
    @Pattern(regexp=ValidationConstants.PHONE, message=ValidationConstants.INVALID_PHONE)
    @Size(max=11,min=10, message=ValidationConstants.INVALID_PHONE_LENGTH)
    private String phone;
    private String address;
    private String comment;
    private String error;
    private String username;
    private String role;

    public EmployeeDTO() {

    }

    public EmployeeDTO(Employee employee) {
        if (employee == null) {
            noteEmployeeNotExist();
            return;
        }

        this.employeeID = employee.getEmployeeID();
        this.firstname = employee.getFirstname();
        this.lastname = employee.getLastname();
        this.email = employee.getEmail();
        this.departmentID = employee.getDepartment().getDepartmentID();
        this.departmentName = employee.getDepartment().getDepartmentName();
        this.gender = employee.getGender();
        this.birth = employee.getBirth();
        this.ssn = employee.getSsn();
        this.marriage = employee.getMarriage();
        this.nationality = employee.getNationality();
        this.education = employee.getEducation();
        this.enrollmentDate = employee.getEnrollmentDate();
        this.position = employee.getPosition();
        this.phone = employee.getPhone();
        this.address = employee.getAddress();
        this.comment = employee.getComment();
        
        if (employee.getUser() == null) {
            this.username = "Not yet assigned";
            this.role = "Not yet assigned";
        } else {
            this.username = employee.getUser().getUsername();
            this.role = employee.getUser().getRole().getRoleName();
        }
    }

    private void noteEmployeeNotExist() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
}
