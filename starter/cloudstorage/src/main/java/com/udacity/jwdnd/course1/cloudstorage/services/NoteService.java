package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.MyNote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final UserMapper userMapper;
    private final NoteMapper noteMapper;

    public NoteService(UserMapper userMapper, NoteMapper noteMapper) {
        this.userMapper = userMapper;
        this.noteMapper = noteMapper;
    }

    public List<MyNote> getListOfNotesFromUser(String userName) {
        Integer userId = userMapper.getUser(userName).getUserId();
        return noteMapper.getListOfNotesFromUser(userId);
    }

    public void addNote(String title, String description, String userName) {
        Integer userId = userMapper.getUser(userName).getUserId();
        MyNote note = new MyNote(0, title, description, userId);
        noteMapper.addNote(note);
    }

    public void editNote(String id, String title, String description, String userName) {
        Integer userId = userMapper.getUser(userName).getUserId();
        MyNote note = new MyNote(Integer.valueOf(id), title, description, userId);
        noteMapper.editNote(note);
    }


//    public Note getNote(Integer noteId) {
//        return noteMapper.getNote(noteId);
//    }
//
//    public void deleteNote(Integer noteId) {
//        noteMapper.deleteNote(noteId);
//    }
//
//    public void updateNote(Integer noteId, String title, String description) {
//        noteMapper.updateNote(noteId, title, description);
//    }

}
