package org.hrsys.controllers;

import org.hrsys.constants.CommonConstants;
import org.hrsys.constants.ServicePaths;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminActionController {
    @RequestMapping(value = ServicePaths.HOME_URL, method = RequestMethod.GET)
    public String showMain() {
        return "welcome";
    }
    
    @RequestMapping(value = ServicePaths.EMPLOYEE_INFO_MANAGEMENT_URL, method = RequestMethod.GET)
    @PreAuthorize(CommonConstants.HAS_ROLE_ADMIN)
    public String showEmployeeInfoManagement() {
        return "employee_info_management";
    }
    
    @RequestMapping(value = ServicePaths.EMPLOYEE_ATTENDANCE_URL, method = RequestMethod.GET)
    @PreAuthorize(CommonConstants.HAS_ROLE_ADMIN) 
    public String showAttendance() {
        return "attendance";
    }
}
