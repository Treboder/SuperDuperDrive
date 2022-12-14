package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final UserMapper userMapper;
    private final NoteMapper noteMapper;
    // mapper initialized by Spring via constructor below

    // Spring initializes the object along with @Service annotation
    public NoteService(UserMapper userMapper, NoteMapper noteMapper) {
        this.userMapper = userMapper;
        this.noteMapper = noteMapper;
    }

    public List<Note> getListOfNotesFromUser(String userName) {
        Integer userId = userMapper.getUser(userName).getUserId();
        return noteMapper.getListOfNotesFromUser(userId);
    }

    public void addNote(String title, String description, String userName) {
        Integer userId = userMapper.getUser(userName).getUserId();
        Note note = new Note(0, title, description, userId);
        noteMapper.addNote(note);
    }

    public void editNote(String id, String title, String description, String userName) {
        Integer userId = userMapper.getUser(userName).getUserId();
        Note note = new Note(Integer.valueOf(id), title, description, userId);
        noteMapper.editNote(note);
    }

    public void deleteNote(Integer noteId) {
        noteMapper.deleteNote(noteId);
    }



}
