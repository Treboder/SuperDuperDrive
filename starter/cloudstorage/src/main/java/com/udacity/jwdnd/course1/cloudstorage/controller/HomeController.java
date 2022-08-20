package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteFormBackingObject;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/home")
public class HomeController {

    //UserService userService;
    FileService fileService;
    NoteService noteService;
    CredentialService credentialService;
    // ... initialized by Spring via constructor below

    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping()
    public String homeView(Authentication authentication,
                           @ModelAttribute("newNote") NoteFormBackingObject newNote,
                           Model model) {

        // todo: show files, notes and credentials from current user only
        model.addAttribute("files", fileService.getAllFiles()) ;
        model.addAttribute("notes", noteService.getAllNoteTitles()) ;
        model.addAttribute("credentials", credentialService.getAllUrls()) ;

        return "home"; // refers to home.html from templates
    }






}
