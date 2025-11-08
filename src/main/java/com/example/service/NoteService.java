package com.example.service;

import com.example.model.Note;
import com.example.model.NoteNotFoundException;

import java.util.Collection;
import java.util.List;

public interface NoteService {

    public Collection<Note> getAllNotes() ;
    public Note add(Note note) ;
    public void deleteById(int id) throws NoteNotFoundException;
    public void update(int id, String title, String content) ;
    public Note getById(int id) ;
    public void addTestNotes();


}