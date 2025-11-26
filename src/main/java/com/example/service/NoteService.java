package com.example.service;

import com.example.model.Note;
import com.example.model.NoteNotFoundException;
import com.example.model.User;
import com.example.notes.dto.create.CreateNoteRequest;
import com.example.notes.dto.create.CreateNoteResponse;
import com.example.notes.dto.delete.DeleteNoteResponse;
import com.example.notes.dto.get.GetUserNotesResponse;
import com.example.notes.dto.update.UpdateNoteRequest;
import com.example.notes.dto.update.UpdateNoteResponse;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface NoteService {

    public CreateNoteResponse create(String username, CreateNoteRequest request) ;

    public GetUserNotesResponse getUserNotes(String username) ;

    public UpdateNoteResponse update(String username, UpdateNoteRequest request) ;

    public DeleteNoteResponse delete(String username, long id) ;

}