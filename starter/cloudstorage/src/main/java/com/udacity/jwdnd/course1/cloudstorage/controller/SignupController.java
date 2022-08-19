package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String signupView(@ModelAttribute User user, Model model) {
        model.addAttribute("signupSuccess", false);
        model.addAttribute("signupError", false);
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute User user, Model model) {
        String signupErrorMessage = null;

        if (!userService.isUsernameAvailable(user.getUsername())) {
            signupErrorMessage = "The username already exists.";
        }

        if (signupErrorMessage == null) {
            int rowsAdded = userService.createUser(user);
            if (rowsAdded < 0) {
                signupErrorMessage = "There was an error signing you up. Please try again.";
            }
        }

        if (signupErrorMessage == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", true);
            model.addAttribute("signupErrorMessage", signupErrorMessage);
        }

        return "signup";
    }
}
