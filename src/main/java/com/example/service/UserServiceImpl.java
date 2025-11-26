package com.example.service;

import com.example.auth.dto.registration.RegistrationRequest;
import com.example.auth.dto.registration.RegistrationResponse;
import com.example.model.Note;
import com.example.model.User;
import com.example.notes.dto.create.CreateNoteRequest;
import com.example.notes.dto.create.CreateNoteResponse;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

private final UserRepository repository;
private final PasswordEncoder passwordEncoder; // если используешь Spring Security

    public User findByUsername(String username) {
        return repository.findByUsername(username)
                .orElse(null);
    }

    public RegistrationResponse register(RegistrationRequest request) {

        // 1. Проверка входных данных
        RegistrationResponse.Error validationError = validate(request);
        if (validationError != RegistrationResponse.Error.ok) {
            return RegistrationResponse.failed(validationError);
        }

        // 2. Проверка, существует ли пользователь
        if (repository.findByUsername(request.getEmail()).isPresent()) {
            return RegistrationResponse.failed(RegistrationResponse.Error.userAlreadyExists);
        }

        // 3. Создание пользователя
        User user = User.builder()
                .username(request.getEmail())                        // email = username
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .age(request.getAge())
                .notes(new ArrayList<>())                            // чтобы не было null
                .build();

        repository.save(user);

        return RegistrationResponse.success();
    }

    private RegistrationResponse.Error validate(RegistrationRequest request) {
        if (request.getEmail() == null ||
                request.getEmail().isBlank() ||
                !request.getEmail().contains("@")) {
            return RegistrationResponse.Error.invalidEmail;
        }

        if (request.getPassword() == null || request.getPassword().length() < 4) {
            return RegistrationResponse.Error.invalidPassword;
        }

        if (request.getName() == null || request.getName().isBlank()) {
            return RegistrationResponse.Error.invalidName;
        }

        if (request.getAge() < 1 || request.getAge() > 120) {
            return RegistrationResponse.Error.invalidAge;
        }

        return RegistrationResponse.Error.ok;
    }

    public User save(User user) {
        return repository.save(user);
    }
}