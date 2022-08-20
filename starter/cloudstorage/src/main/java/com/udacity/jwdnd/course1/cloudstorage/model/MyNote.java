package com.udacity.jwdnd.course1.cloudstorage.model;

public class MyNote {

    private int noteid;                 // auto-incremented id from db
    private String notetitle;           // VARCHAR(20)
    private String notedescription;     // VARCHAR (1000)
    private int userid;                 // foreign key (userid) references USERS(userid)

    // MyBatis calls constructor and getter (via mapper class) for reading/writing the object from/to database
    // Thymeleaf calls getter to render the data in corresponding html

    public MyNote(int id, String title, String description, int user) {
        this.noteid = id;
        this.notetitle = title;
        this.notedescription = description;
        this.userid = user;
    }

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



