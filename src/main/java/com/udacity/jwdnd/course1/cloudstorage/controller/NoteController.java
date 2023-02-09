package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("note")
public class NoteController {

    NoteService noteService; // initialized by Spring via constructor below

    // Spring initializes the object along with @Controller annotation
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("addOrEdit")
    public String addOrEditNote(
            Authentication authentication,
            @ModelAttribute("noteForm") NoteFormBackingObject newNote,
            Model model) {

        String userName = authentication.getName();
        String noteTitle = newNote.getTitle();
        String noteDescription = newNote.getDescription();
        String noteId = newNote.getNoteId();

        if(noteId.isEmpty())
            noteService.addNote(noteTitle, noteDescription, userName);
        else
            noteService.editNote(noteId, noteTitle, noteDescription, userName);

        // always show success message, but should depend on noteService response
        model.addAttribute("changeSuccess", true);
        return "result";
    }

    @GetMapping("delete")
    public ModelAndView deleteNote(@RequestParam(value = "noteid", required = false) String noteid) {
        // pass the noteid as parameter via http://localhost:8080/note/delete?noteid=xyz
        ModelAndView model = new ModelAndView();
        if (noteid != null)
            noteService.deleteNote(Integer.parseInt(noteid));

        // always show success message, but should depend on noteService response
        model.addObject("changeSuccess", true);
        model.setViewName("result");
        return model; // alternatively use "Model model" as input parameter and return String (with "result") instead
    }




}
