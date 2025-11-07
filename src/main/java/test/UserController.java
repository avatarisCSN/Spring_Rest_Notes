package test;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    // 🟢 Create
    @PostMapping
    public User create(@RequestBody User user) {
        return repo.save(user);
    }

    // 🔵 Read all
    @GetMapping
    public List<User> getAll() {
        return repo.findAll();
    }

    // 🟣 Read by ID
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    // 🟠 Update
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return repo.save(user);
    }

    // 🔴 Delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}