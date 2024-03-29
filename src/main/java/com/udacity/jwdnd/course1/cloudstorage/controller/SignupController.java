package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        // create user with some sanity checks
        String signupErrorMessage = null;
        if (!userService.isUsernameAvailable(user.getUsername()))
            signupErrorMessage = "The username already exists.";
        else {
            int rowsAdded = userService.createUser(user);
            if (rowsAdded < 0)
                signupErrorMessage = "There was an error signing you up. Please try again.";
        }

        // handle success/error messages and redirect
        if (signupErrorMessage == null) {
            model.addAttribute("signupSuccess", true);
            return "redirect:/login";
            // ToDo: check why model param does not lead to proper success message, try flash attributes:
            //      https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/redirect-attributes.html
        }
        else {
            model.addAttribute("signupError", true);
            model.addAttribute("signupErrorMessage", signupErrorMessage);
        }

        return "signup";
    }



}


