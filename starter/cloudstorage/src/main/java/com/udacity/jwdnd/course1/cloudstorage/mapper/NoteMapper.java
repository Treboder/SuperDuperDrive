package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{id}")
    List<Note> getListOfNotesFromUser(String id);

    @Insert("INSERT INTO NOTES (NOTETITLE, NOTEDESCRIPTION, USERID) VALUES(#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int saveNote(Note note);

}

