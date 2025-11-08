package com.example.controller;

import com.example.model.Note;
import com.example.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    // Інжектимо NoteService через конструктор
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public ModelAndView getAllNotes() {
        ModelAndView result = new ModelAndView("note-list"); // note-list.html в templates/
        result.addObject("notes", noteService.getAllNotes()); // Передаём коллекцию заметок
        return result;
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam int id) {
        noteService.deleteById(id);
        return "redirect:/note/list"; // После удаления — редирект
    }

    @GetMapping("/edit")
    public ModelAndView editNoteForm(@RequestParam int id) {
        ModelAndView result = new ModelAndView("note-edit.html"); // note-edit.html.html
        result.addObject("note", noteService.getById(id));
        return result;
    }

    @PostMapping("/edit")
    public String saveNote(@RequestParam int id,
                           @RequestParam String title,
                           @RequestParam String content) {
         noteService.update(id, title, content);
        return "redirect:/note/list";
    }
    @GetMapping("/inject")
    public String injectNotes() {
        noteService.addTestNotes();
        return "redirect:/note/list"; // После добавления — редирект
    }
}
