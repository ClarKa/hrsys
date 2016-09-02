package org.hrsys.controllers;

import java.security.Principal;

import org.hrsys.constants.ServicePaths;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.hrsys.facades.LoginFacade;

@Controller
public class LoginController {
    @RequestMapping(ServicePaths.LOGIN_URL)
    public String showLogin(
        @RequestParam(value = "error", required = false) String error,
        @RequestParam(value = "logout", required = false) String logout,
        Principal principal,
        ModelMap model) {

	LoginFacade.performLogin(error, logout, model);

	return "login";
    }

    @RequestMapping(ServicePaths.ACCESS_DENIED_URL)
    public String showDenied() {
	   return "deny";
    }
}