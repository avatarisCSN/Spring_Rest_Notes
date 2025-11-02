package com.example.service;

import com.example.model.Note;
import com.example.model.NoteNotFoundException;
import java.util.List;

public interface NoteService {
    public List<Note> listAll() ;
    public Note add(Note note) ;
    public void deleteById(int id) throws NoteNotFoundException;
    public void update(Note note) ;
    public Note getById(int id) ;


}