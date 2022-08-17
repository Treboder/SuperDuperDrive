package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationProvider userService;

    public LoginController(AuthenticationProvider userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("loginError", true);
        }

        if (logout != null) {
            model.addObject("logoutSuccess", true);
        }

        model.setViewName("login");
        return model;
    }

//    @GetMapping()
//    public String loginView(@ModelAttribute User user, Model model) {
//        model.addAttribute("logoutSuccess", false);
//        model.addAttribute("loginError", false);
//
//        System.out.println(user.getUsername() + " tries to login with " + user.getPassword());
//        //Authentication authentication
//        //userService.
//
//        return "login";
//    }

}
