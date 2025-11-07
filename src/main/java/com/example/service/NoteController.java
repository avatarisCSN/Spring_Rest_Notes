package com.example.service;

import com.example.model.Note;
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

    // Внутренняя память (имитация базы данных)
    private final Map<Integer, Note> notes = new HashMap<>();

    public NoteController() {
        // Заполним тестовыми данными
        notes.put(1, new Note(1,"Первая заметка", "Привет111, это 111тест"));
        notes.put(2, new Note(2,"Вторая заметка", "Пример 222другой222 заметки"));
        notes.put(3, new Note(3,"Третяя заметка", "Бла-бла333-бла333-бла"));
        notes.put(4, new Note(4,"Четвертая заметка", "4-4блин-блун-4-4-444"));
    }


    @GetMapping("/list")
    public ModelAndView getAllNotes() {
        ModelAndView result = new ModelAndView("note-list"); // note-list.html в templates/
        result.addObject("notes", notes.values()); // Передаём коллекцию заметок
        return result;
    }


    @PostMapping("/delete")
    public String deleteNote(@RequestParam int id) {
        notes.remove(id);
        return "redirect:/note/list"; // После удаления — редирект
    }


    @GetMapping("/edit")
    public ModelAndView editNoteForm(@RequestParam int id) {
        ModelAndView result = new ModelAndView("note-edit.html"); // note-edit.html.html
        result.addObject("note", notes.get(id));
        return result;
    }


    @PostMapping("/edit")
    public String saveNote(@RequestParam int id,
                           @RequestParam String title,
                           @RequestParam String content) {
        Note note = notes.get(id);
        if (note != null) {
            note.setTitle(title);
            note.setContent(content);
        }
        return "redirect:/note/list";
    }
}
