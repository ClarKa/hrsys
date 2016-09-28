package org.hrsys.dto;

import java.util.Collection;

import org.hrsys.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDTO extends User {
    /**
     * 
     */
    private static final long serialVersionUID = -3531439484732724601L;
    
    private final EmployeeDTO employee;

    public UserDTO(String username, String password, boolean enabled,
        boolean accountNonExpired, boolean credentialsNonExpired,
        boolean accountNonLocked,
        Collection authorities,
        EmployeeDTO employee) {

            super(username, password, enabled, accountNonExpired,
               credentialsNonExpired, accountNonLocked, authorities);

            this.employee = employee;
    }

    public static long getSerialversionuid() {
       return serialVersionUID;
    }

    /**
     * @return the employee
     */
    public EmployeeDTO getEmployee() {
        return employee;
    }
}
