package org.hrsys.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "department")
public class Department implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int departmentID;
    private String departmentName;
    private List<Employee> departmentEmployees;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dp_department_id", unique = true, nullable = false, insertable = true, updatable = true)
    public int getDepartmentID()       { return departmentID; }

    @Column(name = "dp_department_name", unique = true, nullable = false, insertable = true, updatable = true)
    public String getDepartmentName()  { return departmentName; }

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "department")
    @JsonIgnore
    public List<Employee> getDepartmentEmployees()     { return departmentEmployees; }

    public void setDepartmentID(int s)       		     { departmentID = s; }
    public void setDepartmentName(String s)             { departmentName = s.trim(); }
    public void setDepartmentEmployees(List<Employee> s)  { departmentEmployees = s; }
}
