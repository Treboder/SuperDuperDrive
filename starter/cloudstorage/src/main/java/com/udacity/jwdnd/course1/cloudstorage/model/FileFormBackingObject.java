package com.udacity.jwdnd.course1.cloudstorage.model;

import org.springframework.web.multipart.MultipartFile;

public class FileFormBackingObject {

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    private String test = "ahahahahaha";

    public String getTest() {
        //return test;
        if(file == null)
            return "1"; //""file is null";
        else
            return file.getName();
    }



}