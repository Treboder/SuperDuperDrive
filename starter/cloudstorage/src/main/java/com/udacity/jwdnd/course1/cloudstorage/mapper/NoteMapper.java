package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.MyNote;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoteMapper {

    //@Select("SELECT notetitle FROM NOTES")
    //List<String> getAllNoteTitles();

    @Select("SELECT * FROM NOTES")
    List<MyNote> getAllNotes();

    @Select("SELECT * FROM NOTES WHERE userid = #{id}")
    List<MyNote> getListOfNotesFromUser(Integer id);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int addNote(MyNote note);

}

