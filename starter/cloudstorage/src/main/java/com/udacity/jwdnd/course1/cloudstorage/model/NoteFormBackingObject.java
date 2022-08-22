package com.udacity.jwdnd.course1.cloudstorage.model;

public class NoteFormBackingObject
{
    private String noteId;          // INT auto-incremented by db
    private String title;           // VARCHAR(20)
    private String description;     // VARCHAR (1000)

    // getter lets the NoteController get the form input from home.html,
    // setter are used ... todo: where? why?
    // then passes the fields to the NoteService creating the Note object (which is the persisted in db via mapper)
    // Rendering in home.html uses the note model object passed by HomeController, more precisely the list of note model objects

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
