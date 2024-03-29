package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialFormBackingObject;
import com.udacity.jwdnd.course1.cloudstorage.model.FileFormBackingObject;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteFormBackingObject;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
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


    FileService fileService;
    NoteService noteService;
    CredentialService credentialService;
    EncryptionService encryptionService;
    // ... initialized by Spring via constructor below

    public HomeController(FileService fileService,
                          NoteService noteService,
                          CredentialService credentialService,
                          EncryptionService encryptionService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping()
    public String homeView(Authentication authentication,
                           // model attributes not used here, but required by thymeleaf to render home.html properly
                           @ModelAttribute("fileForm") FileFormBackingObject newFile,
                           @ModelAttribute("noteForm") NoteFormBackingObject newNote,
                           @ModelAttribute("credentialForm") CredentialFormBackingObject newCredential,
                           Model model) {

        model.addAttribute("files", fileService.getListOfFilesFromUser(authentication.getName())) ;
        model.addAttribute("notes", noteService.getListOfNotesFromUser(authentication.getName())) ;
        model.addAttribute("credentials", credentialService.getListOfCredentialsFromUser(authentication.getName())) ;
        model.addAttribute("encryption", encryptionService); // is called from home.html when rendering the password

        return "home"; // refers to home.html from templates
    }






}
