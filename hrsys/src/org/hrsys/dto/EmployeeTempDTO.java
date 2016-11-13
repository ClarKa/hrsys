package org.hrsys.dto;

import java.sql.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hrsys.constants.ValidationConstants;
import org.hrsys.entity.EmployeeTemp;

public class EmployeeTempDTO {
    private int employeeID;
    private String firstname;

    @Pattern(regexp=ValidationConstants.PURE_WORD, message=ValidationConstants.INVALID_EMPLOYEE_NAME)
    private String lastname;

    @Pattern(regexp=ValidationConstants.GENDER, message=ValidationConstants.INVALID_GENDER)
    private String gender;

    @Email(message=ValidationConstants.INVALID_EMAIL)
    private String email;
    private Integer departmentID;
    private Date birth;
    private String ssn;
    private String marriage;
    private String nationality;
    private String education;
    private Date enrollmentDate;

    @Pattern(regexp=ValidationConstants.PURE_WORD_OR_EMPTY, message=ValidationConstants.INVALID_POSITION)
    private String position;

    @Pattern(regexp=ValidationConstants.PHONE, message=ValidationConstants.INVALID_PHONE)
    @Size(max=11,min=10, message=ValidationConstants.INVALID_PHONE_LENGTH)
    private String phone;
    private String error;
    
    public EmployeeTempDTO() {
        
    }
    
    public EmployeeTempDTO(EmployeeTemp temp) {
        if (temp == null) {
            this.error = "No request found!";
            return;
        }
        
        this.employeeID = temp.getEmployeeID();
        this.firstname = temp.getFirstname();
        this.lastname = temp.getLastname();
        this.email = temp.getEmail();
        this.departmentID = temp.getDepartmentID();
        this.gender = temp.getGender();
        this.birth = temp.getBirth();
        this.ssn = temp.getSsn();
        this.marriage = temp.getMarriage();
        this.nationality = temp.getNationality();
        this.education = temp.getEducation();
        this.enrollmentDate = temp.getEnrollmentDate();
        this.position = temp.getPosition();
        this.phone = temp.getPhone();
    }
    /**
     * @return the employeeID
     */
    public int getEmployeeID() {
        return employeeID;
    }
    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }
    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }
    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }
    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the departmentID
     */
    public Integer getDepartmentID() {
        return departmentID;
    }
    /**
     * @param departmentID the departmentID to set
     */
    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }
    /**
     * @return the birth
     */
    public Date getBirth() {
        return birth;
    }
    /**
     * @param birth the birth to set
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    /**
     * @return the ssn
     */
    public String getSsn() {
        return ssn;
    }
    /**
     * @param ssn the ssn to set
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    /**
     * @return the marriage
     */
    public String getMarriage() {
        return marriage;
    }
    /**
     * @param marriage the marriage to set
     */
    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }
    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }
    /**
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    /**
     * @return the education
     */
    public String getEducation() {
        return education;
    }
    /**
     * @param education the education to set
     */
    public void setEducation(String education) {
        this.education = education;
    }
    /**
     * @return the enrollmentDate
     */
    public Date getEnrollmentDate() {
        return enrollmentDate;
    }
    /**
     * @param enrollmentDate the enrollmentDate to set
     */
    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }
    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }
    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }
    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * @return the error
     */
    public String getError() {
        return error;
    }
    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }
}
