package com.udacity.jwdnd.course1.cloudstorage.model;

public class NoteFormBackingObject
{
    private String noteId;
    private String title;           // VARCHAR(20)
    private String description;     // VARCHAR (1000)

    // getter setter lets the NoteController get the form input from home.html,
    // then passes the fields to the NoteService creating the Note object

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
