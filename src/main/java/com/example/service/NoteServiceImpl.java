package com.example.service;


import com.example.model.Note;
import com.example.model.NoteNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteServiceImpl implements NoteService {
    final Map<Integer, Note> notes = new HashMap<>();

    public Collection<Note> getAllNotes() {
        return notes.values();
    }
    public Note add(Note note) {
        int n = (int)(Math.random() * 1000);
        notes.put(n, note);
        return note;
    }
    public void deleteById(int id) throws NoteNotFoundException {
        notes.remove(id);
    }
    public void update(int id, String title, String content) throws NoteNotFoundException {
        Note note = notes.get(id);
        if (note != null) {
            note.setTitle(title);
            note.setContent(content);
        }
    }
    public Note getById(int id) throws NoteNotFoundException {
       return notes.get(id);
    }
    public void addTestNotes() {
        notes.put(1,new Note(1,"Title 1", "Content 1"));
        notes.put(2,new Note(1,"Title 2", "Content 2"));
    }

}