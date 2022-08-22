package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {

    private int credentialID;           // Int auto-incremented by db
    private String url;                 // VARCHAR(100)
    private String username;            // VARCHAR (30)
    private String key;                 // VARCHAR
    private String password;            // VARCHAR
    private int userId;                 // INT with foreign key (userid) references USERS(userid)

    // MyBatis calls constructor and getter (via mapper class) for reading/writing the object from/to database
    // Thymeleaf calls getter to render the data in corresponding html

    public Credential(int credentialID, String url, String username, String key, String password, int userId) {
        this.credentialID = credentialID;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userId = userId;
    }

    public int getCredentialID() {
        return credentialID;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getKey() {
        return key;
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return userId;
    }
}
