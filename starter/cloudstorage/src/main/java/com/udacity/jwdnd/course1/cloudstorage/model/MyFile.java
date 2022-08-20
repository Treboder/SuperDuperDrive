package com.udacity.jwdnd.course1.cloudstorage.model;

public class MyFile {

    private int fileID;                 // auto-incremented by db
    private String fileName;            // VARCHAR
    private String contentType;         // VARCHAR
    private String fileSize;            // VARCHAR
    private int userID;                 // INT with foreign key (userid) references USERS(userid)
    private Byte[] fileData;            // BLOB

    // MyBatis calls constructor and getter (via mapper class) for reading/writing the object from/to database
    // Thymeleaf calls getter to render the data in corresponding html

    public MyFile(int fileID, String fileName, String contentType, String fileSize, int userID, Byte[] fileData) {
        this.fileID = fileID;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userID = userID;
        this.fileData = fileData;
    }

    public int getFileID() {
        return fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public int getUserID() {
        return userID;
    }

    public Byte[] getFileData() {
        return fileData;
    }

}
