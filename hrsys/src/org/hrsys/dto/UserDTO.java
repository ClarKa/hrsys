package org.hrsys.dto;

import java.util.Collection;

import org.springframework.security.core.userdetails.User;

public class UserDTO extends User {
    /**
     * 
     */
    private static final long serialVersionUID = -3531439484732724601L;
    
    private final int employeeID;
    private final String firstname;
    private final String lastname;

    public UserDTO(String username, String password, boolean enabled,
        boolean accountNonExpired, boolean credentialsNonExpired,
        boolean accountNonLocked,
        Collection authorities,
        EmployeeDTO employeeDto) {

            super(username, password, enabled, accountNonExpired,
               credentialsNonExpired, accountNonLocked, authorities);

            this.employeeID = employeeDto.getEmployeeID();
            this.firstname = employeeDto.getFirstname();
            this.lastname = employeeDto.getLastname();
    }

    public static long getSerialversionuid() {
       return serialVersionUID;
    }

    /**
     * @return the employeeID
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }
}
