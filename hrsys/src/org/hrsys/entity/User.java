package org.hrsys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "username", unique = true, nullable = false, insertable = true, updatable = true)
    private String username;
    
    @Column(name = "password", unique = true, nullable = false, insertable = true, updatable = true)
    private String password;
    
    @Column(name = "enabled", unique = true, nullable = false, insertable = true, updatable = true)
    private boolean enabled;
    
//    @Column(name = "us_employee_id", unique = true, nullable = true, insertable = true, updatable = true)
//    private int employeeID;

    @OneToOne(cascade = {}, optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "us_employee_id", unique = false, nullable = true, insertable = true, updatable = true)
    private Employee employee;
    
    @OneToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "us_role_id", unique = false, nullable = false, insertable = true, updatable = true)
    private Role role;

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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
