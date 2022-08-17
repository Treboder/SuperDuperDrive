package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationProvider userService;

    public LoginController(AuthenticationProvider userService) {
        this.userService = userService;
    }

    @GetMapping()
    //public String loginView(Model model) {
    public String loginView(@ModelAttribute User user, Model model) {
        model.addAttribute("logoutSuccess", false);
        model.addAttribute("loginError", false);

        System.out.println(user.getUsername() + " tries to login with " + user.getPassword());
        //Authentication authentication
        //userService.

        return "login";
    }

    @PostMapping()
    public String loginUser(@ModelAttribute User user, Model model) {
        System.out.println("login procedure");
        return "login";
    }


}
