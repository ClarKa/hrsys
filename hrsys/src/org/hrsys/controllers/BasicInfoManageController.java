package org.hrsys.controllers;

import org.hrsys.constants.ServicePaths;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicInfoManageController {
    @RequestMapping(ServicePaths.HOME_URL)
    public String showMain() {
	return "index";
    }
}
