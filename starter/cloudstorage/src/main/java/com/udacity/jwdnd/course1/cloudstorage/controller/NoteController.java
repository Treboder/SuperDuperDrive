package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("note")
public class NoteController {

    NoteService noteService; // initialized by Spring via constructor below

    public NoteController(NoteService noteService) { this.noteService = noteService; }

    @PostMapping("add")
    public String addNote(
            Authentication authentication,
            @ModelAttribute("newNote") NoteFormBackingObject newNote,
            Model model) {

        String userName = authentication.getName();
        String noteTitle = newNote.getTitle();
        String noteDescription = newNote.getDescription();

        //String noteId ?
        System.out.println("NOTECONTROLLER --> add note: " + newNote.getTitle());

        noteService.addNote(noteTitle, noteDescription, userName);
        model.addAttribute("changeSuccess", true);
        return "result";
    }


}
