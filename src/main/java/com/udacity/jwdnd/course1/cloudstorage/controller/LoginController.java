package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {

        if (error != null)
            model.addAttribute("loginError", true);

        return "login";
    }

//    Alternative implementation using ModelAndView
//
//    @GetMapping()
//    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error) {
//
//        ModelAndView model = new ModelAndView();
//        if (error != null)
//            model.addObject("loginError", true);
//
//        model.setViewName("login");
//        return model;
//    }



}
