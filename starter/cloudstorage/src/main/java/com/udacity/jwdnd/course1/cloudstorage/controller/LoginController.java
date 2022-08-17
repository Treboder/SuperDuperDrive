package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping()
    public String loginView(Model model) {
    //public String loginView(@ModelAttribute User user, Model model) {
        model.addAttribute("logoutSuccess", false);
        model.addAttribute("loginError", false);
        return "login";
    }

    @PostMapping()
    public String loginUser(@ModelAttribute User user, Model model) {
        String signupError = null;
        //f (!userService.isUsernameAvailable(user.getUsername())) {


        return "login";
    }


}
