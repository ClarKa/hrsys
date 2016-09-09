package org.hrsys.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee")
public class Employee implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "em_employee_id", unique = true, nullable = false, insertable = true, updatable = true)
    private int employeeID;

    @Column(name = "em_firstname", unique = false, nullable = false, insertable = true, updatable = true)
    private String firstname;

    @Column(name = "em_lastname", unique = false, nullable = false, insertable = true, updatable = true)
    private String lastname;

    @Column(name = "em_gender", unique = false, nullable = true, insertable = true, updatable = true)
    private String gender;

    @Column(name = "em_birth", unique = false, nullable = true, insertable = true, updatable = true)
    private Date birth;

    @Column(name = "em_ssn", unique = true, nullable = true, insertable = true, updatable = true)
    private String ssn;

    @Column(name = "em_marriage", unique = false, nullable = true, insertable = true, updatable = true)
    private String marriage;

    @Column(name = "em_nationality", unique = false, nullable = true, insertable = true, updatable = true)
    private String nationality;

    @Column(name = "em_education", unique = false, nullable = true, insertable = true, updatable = true)
    private String education;

    @Column(name = "em_enrollment_date", unique = false, nullable = true, insertable = true, updatable = true)
    private Date enrollmentDate;

    @Column(name = "em_email", unique = true, nullable = true, insertable = true, updatable = true)
    private String email;

    @Column(name = "em_position", unique = false, nullable = true, insertable = true, updatable = true)
    private String position;

    @Column(name = "em_phone", unique = true, nullable = true, insertable = true, updatable = true)
    private String phone;

    @Column(name = "em_address", unique = false, nullable = true, insertable = true, updatable = true)
    private String address;

    @Column(name = "em_comment", unique = false, nullable = true, insertable = true, updatable = true)
    private String comment;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "em_department_id", unique = false, nullable = true, insertable = true, updatable = true)
    @JsonIgnore
    private Department department;


    public int getEmployeeID()         { return employeeID; }
    public String getFirstname()  { return firstname; }
    public String getLastname()   { return lastname; }
    public String getGender()   { return gender; }
    public Date getBirth()   { return birth; }
    public String getSsn()   { return ssn; }
    public String getMarriage()  { return marriage; }
    public String getNationality()  { return nationality; }
    public String getEducation() { return education; }
    public Date getEnrollmentDate()  { return enrollmentDate; }
    public String getEmail()      { return email; }
    public String getPosition()   { return position; }
    public String getPhone()  { return phone; }
    public String getAddress()  { return address; }
    public String getComment() { return comment; }
    public Department getDepartment() { return department; }

    public void setEmployeeID(int s)           { employeeID = s; }
    public void setFirstname(String s)    { firstname = s.trim(); }
    public void setLastname(String s)     { lastname = s.trim(); }
    public void setGender(String s)       { gender = s.trim(); }
    public void setBirth(Date s)          { birth = s; }
    public void setSsn(String s)          { ssn = s; }
    public void setMarriage(String s)     { marriage = s; }
    public void setNationality(String s)  { nationality = s; }
    public void setEducation(String s)    { education = s; }
    public void setEnrollmentDate(Date s) { enrollmentDate = s; }
    public void setEmail(String s)        { email = s.trim(); }
    public void setPosition(String s)     { position = s; }
    public void setPhone(String s)        { phone = s; }
    public void setAddress(String s)      { address = s; }
    public void setComment(String s)      { comment = s; }
    public void setDepartment(Department d)        { department = d; }
}
