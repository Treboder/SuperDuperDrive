package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/result")
public class ResultController {

    private final AuthenticationProvider userService;

    public ResultController(AuthenticationProvider userService) {
        this.userService = userService;
    }

    // Three Variants to show
    // Your changes were successfully saved. Click here to continue
    // Your changes were not saved. Click here to continue
    // Example Error Message. Click here to continue

//    @GetMapping()
//    public String resultPage() {
//        return "result";
//    }

    @GetMapping()
    //public ModelAndView resultPage(@RequestParam(value = "error", required = false) String error) {
    public ModelAndView resultPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("changeSuccess", true);
        model.addObject("errorMessage", "my error message"); // empty string leads to standard error message
        model.setViewName("result");
        return model;
    }


}


