package org.hrsys.webservices;

import java.sql.SQLException;

import org.hrsys.constants.CommonConstants;
import org.hrsys.constants.ServicePaths;
import org.hrsys.dao.EmployeeManager;
import org.hrsys.dao.UserManager;
import org.hrsys.entity.CustomUser;
import org.hrsys.entity.Employee;
import org.hrsys.entity.Role;
import org.hrsys.enums.RoleEnum;
import org.hrsys.helpers.MetaAnnotations.IsAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ServicePaths.GET_ACCOUNT_PATH)
public class AccountServices {
    @Autowired
    UserManager userManager;
    
    @Autowired
    EmployeeManager employeeManager;
    
    @RequestMapping(value =  "/{employeeid}", method = RequestMethod.POST, produces = "application/json")
    @IsAdmin
    public String activateAccount(@PathVariable int employeeId, @RequestParam RoleEnum roleEnum) {
        Employee employee = employeeManager.getOneEmployee(employeeId);
        if (employee == null) {
            return "No such employee in profile.";
        } else if (employee.getUser() != null) {
            return "Employee already has an active account.";
        } else {
            CustomUser user = new CustomUser();
            Role role = new Role();
            role.setRoleId(roleEnum.getValue());
            
            user.setEmployee(employee);
            user.setUsername(employee.getFirstname() + " " + employee.getLastname());
            user.setRole(role);
            user.setEnabled(true);
            user.setPassword(CommonConstants.DEFAULT_PASSWORD);
            
            try {
                userManager.createUser(user);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return "";
        }
    }
}
