package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("note")
public class NoteController {

    //private final NoteService noteService;
    //private final UserService userService;

//    public NoteController(NoteService noteService, UserService userService) {
//        this.noteService = noteService;
//        this.userService = userService;
//    }

    public NoteController() {}

    @GetMapping
    public String getHomePage(
            Authentication authentication,
            @ModelAttribute("newNote") NoteFormBackingObject newNote,
            Model model) {

        return "home";
    }



    @PostMapping("add-note")
    public String newNote(
            Authentication authentication,
            @ModelAttribute("newNote") NoteFormBackingObject newNote,
            Model model) {
        String userName = authentication.getName();
        String newTitle = newNote.getTitle();
        String noteIdStr = newNote.getNoteId();
        String newDescription = newNote.getDescription();

        model.addAttribute("changeSuccess", true);
        return "result";
    }


}
