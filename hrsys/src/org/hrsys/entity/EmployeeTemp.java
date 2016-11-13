package org.hrsys.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee_temp")
public class EmployeeTemp {
    @Id
    @Column(name = "emt_employee_id", unique = true, nullable = false, insertable = true, updatable = true)
    private int employeeID;

    @Column(name = "emt_firstname", unique = false, nullable = false, insertable = true, updatable = true)
    private String firstname;

    @Column(name = "emt_lastname", unique = false, nullable = false, insertable = true, updatable = true)
    private String lastname;

    @Column(name = "emt_gender", unique = false, nullable = true, insertable = true, updatable = true)
    private String gender;

    @Column(name = "emt_birth", unique = false, nullable = true, insertable = true, updatable = true)
    private Date birth;

    @Column(name = "emt_ssn", unique = true, nullable = true, insertable = true, updatable = true)
    private String ssn;

    @Column(name = "emt_marriage", unique = false, nullable = true, insertable = true, updatable = true)
    private String marriage;

    @Column(name = "emt_nationality", unique = false, nullable = true, insertable = true, updatable = true)
    private String nationality;

    @Column(name = "emt_education", unique = false, nullable = true, insertable = true, updatable = true)
    private String education;

    @Column(name = "emt_enrollment_date", unique = false, nullable = true, insertable = true, updatable = true)
    private Date enrollmentDate;

    @Column(name = "emt_email", unique = true, nullable = true, insertable = true, updatable = true)
    private String email;

    @Column(name = "emt_position", unique = false, nullable = true, insertable = true, updatable = true)
    private String position;

    @Column(name = "emt_phone", unique = true, nullable = true, insertable = true, updatable = true)
    private String phone;

    @Column(name = "emt_address", unique = false, nullable = true, insertable = true, updatable = true)
    private String address;

    @Column(name = "emt_comment", unique = false, nullable = true, insertable = true, updatable = true)
    private String comment;

    @Column(name = "emt_department_id", unique = false, nullable = true, insertable = true, updatable = true)
    private Integer departmentID;

    @OneToOne(cascade = {}, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "emt_employee_id", unique = false, nullable = true, insertable = true, updatable = true)
    private Employee employee;

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
    public Integer getDepartmentID() { return departmentID; }
    public Employee getEmployee() { return employee; }

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
    public void setDepartmentID(Integer d)        { departmentID = d; }
    public void setEmployee(Employee e)   { employee = e; }
}
