package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.MyFile;
import com.udacity.jwdnd.course1.cloudstorage.model.MyNote;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT fileId FROM FILES")
    List<String> getAllFileNames();

    @Select("SELECT * FROM FILES WHERE userId = #{id}")
    List<MyFile> getListOfFilesFromUser(Integer id);

    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userID, fileData) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userid}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    int addNote(MyNote note);

}

