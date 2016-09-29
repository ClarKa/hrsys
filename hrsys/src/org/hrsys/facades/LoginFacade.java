package org.hrsys.facades;

import org.springframework.ui.ModelMap;

public class LoginFacade {
    public static void performLogin(String error, String logout, ModelMap model) {
        if (error != null) {
            model.addAttribute("error", "invalid username or password.");
        }

        if (logout != null) {
            model.addAttribute("msg", "You have been logged out.");
        }
    }
}
