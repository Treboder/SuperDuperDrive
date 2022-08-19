package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoteMapper {


    @Select("SELECT NOTETITLE FROM NOTES")
    List<String> getAllNoteTitles();

    @Select("SELECT * FROM NOTES WHERE userid = #{id}")
    List<Note> getListOfNotesFromUser(Integer id);

    @Insert("INSERT INTO NOTES (NOTETITLE, NOTEDESCRIPTION, USERID) VALUES(#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int addNote(Note note);

}

