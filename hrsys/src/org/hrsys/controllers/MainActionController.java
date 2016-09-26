package org.hrsys.controllers;

import org.hrsys.constants.ServicePaths;
import org.hrsys.helpers.MetaAnnotations.IsAdmin;
import org.hrsys.helpers.MetaAnnotations.IsAuthenticated;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainActionController {
    @RequestMapping(value = ServicePaths.HOME_URL, method = RequestMethod.GET)
    @IsAuthenticated
    public String showWelcome() {
        return "template_top";
    }

    @RequestMapping(value = ServicePaths.EMPLOYEE_INFO_URL, method = RequestMethod.GET)
    @IsAdmin
    public String showEmployeeInfoManagement() {
        return "employee_info";
    }

    @RequestMapping(value = ServicePaths.EMPLOYEE_ATTENDANCE_URL, method = RequestMethod.GET)
    @IsAuthenticated
    public String showAttendance() {
        return "attendance";
    }
}
