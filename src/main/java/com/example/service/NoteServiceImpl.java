package com.example.service;


import com.example.model.Note;
import com.example.model.NoteNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    final List<Note> repository = new ArrayList<>();

    public List<Note> listAll() {
        return repository;
    }

    public Note add(Note note) {
        int n = (int)(Math.random() * 1000);
        note.setId(n);
        repository.add(note);
        return note;
    }

    public void deleteById(int id) throws NoteNotFoundException {
        Note note = repository.stream()
                .filter(n -> n.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoteNotFoundException(id));
        repository.remove(note);
    }

    public void update(Note note) throws NoteNotFoundException {
        Note noteBuf = repository.stream()
                .filter(n -> n.getId() == note.getId())
                .findFirst()
                .orElseThrow(() -> new NoteNotFoundException(note.getId()));
        noteBuf.setContent(note.getContent());
        noteBuf.setTitle(note.getTitle());
    }

    public Note getById(int id) throws NoteNotFoundException {
        Note noteBuf = repository.stream()
                .filter(n -> n.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoteNotFoundException(id));
        return noteBuf;
    }

}