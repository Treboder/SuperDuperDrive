package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {

    private int noteid;                 // auto-incremented id from db
    private String notetitle;           // VARCHAR(20)
    private String notedescription;     // VARCHAR (1000)
    private int userid;                 // foreign key (userid) references USERS(userid)

    public Note(int id, String title, String description, int user) {
        this.noteid = id;
        this.notetitle = title;
        this.notedescription = description;
        this.userid = user;
    }

    // getter called by NoteMapper when inserting/creating a new record in db (setter not required),
    // but also called by Thymeleaf in order to feed home.html

    public int getNoteid() {
        return noteid;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public String getNotedescription() {
        return notedescription;
    }

    public int getUserid() {
        return userid;
    }

}



