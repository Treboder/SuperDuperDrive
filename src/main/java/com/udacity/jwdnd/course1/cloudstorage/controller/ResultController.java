package com.udacity.jwdnd.course1.cloudstorage.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/result")
public class ResultController {

    @GetMapping()
    public ModelAndView resultPage(@RequestParam(value = "error", required = false) String error) {
        ModelAndView model = new ModelAndView();

        if(error == null)
            model.addObject("changeSuccess", true);

        // pass a customized error message as parameter via http://localhost:8080/result?error=my_personal_error_message
        else if (error != "") {
            model.addObject("changeSuccess", false);
            model.addObject("errorMessage", error);
        }

        else // empty string leads to standard error message
        {
            model.addObject("changeSuccess", false);
            model.addObject("errorMessage", "standard error message");
        }

        model.setViewName("result"); // ToDo: pass parameter to automatically activate the last used tab
        return model;
    }


}


