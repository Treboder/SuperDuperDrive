package com.udacity.jwdnd.course1.cloudstorage.model;

public class CredentialFormBackingObject
{
    private String credentialId;           // Int auto-incremented by db
    private String url;                 // VARCHAR(100)
    private String username;            // VARCHAR (30)
    private String key;                 // VARCHAR
    private String password;            // VARCHAR
    private String userId;                 // INT with foreign key (userid) references USERS(userid)

    // getter lets the CredentialController get the form input from home.html,
    // setter are used ... todo: where? why?
    // then passes the fields to the CredentialService creating the Credential object (which is the persisted in db via mapper)
    // Rendering in home.html uses the credential model object passed by HomeController, more precisely the list of credential model objects

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
