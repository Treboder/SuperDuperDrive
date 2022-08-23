package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface FileMapper {

    // uses the getter methods from file model object map the objects to db structure
    // e.g. userID via getUserID() and userid via getUserid (small/capital letter matter!)

    @Select("SELECT * FROM FILES WHERE userId = #{id}")
    List<File> getListOfFilesFromUser(Integer id);

    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userID, fileData) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userID}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileID")
    int addFile(File file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    void deleteFile(Integer fileId);

    @Select("SELECT * FROM FILES WHERE fileID = #{fileID}")
    File getFile(String fileID);


}

