package org.hrsys.helpers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;  
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.hrsys.dao.EmployeeManager;
import org.hrsys.dao.UserManager;
import org.hrsys.dto.EmployeeDTO;
import org.hrsys.dto.UserDTO;
import org.hrsys.entity.CustomUser;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserManager userManager;
    
    @Autowired
    EmployeeManager employeeManager;

    public UserDetails loadUserByUsername(String username)
             throws UsernameNotFoundException, DataAccessException {
        
        CustomUser user = userManager.getOneUser(username);
        EmployeeDTO employeeDto = new EmployeeDTO(user.getEmployee());
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
        
        UserDTO userDto = new UserDTO(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authList, employeeDto);
        
        return userDto;
   }

}
