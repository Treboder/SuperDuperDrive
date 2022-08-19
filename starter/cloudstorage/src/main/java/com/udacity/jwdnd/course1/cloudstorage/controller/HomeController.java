package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping()
    public String homeView(Model model) {
        model.addAttribute("files", getFiles()) ;
        model.addAttribute("notes", getNotes()) ;
        model.addAttribute("credentials", getCredentials()) ;
        model.addAttribute("test", "test") ;
        return "home"; // refers to home1.html from templates
    }

    private ArrayList<String> getNotes() {
        ArrayList<String> notes = new ArrayList<String>();
        notes.add("noteA");
        notes.add("noteB");
        return notes;
    }

    private ArrayList<String> getFiles() {
        ArrayList<String> files = new ArrayList<String>();
        files.add("fileA");
        files.add("fileB");
        return files;
    }

    private ArrayList<String> getCredentials() {
        ArrayList<String> credentials = new ArrayList<String>();
        credentials.add("credA");
        credentials.add("credB");
        return credentials;
    }

}
