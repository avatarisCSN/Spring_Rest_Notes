package com.example.service;

import com.example.auth.dto.registration.RegistrationRequest;
import com.example.auth.dto.registration.RegistrationResponse;
import com.example.model.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    public User findByUsername(String username) ;
    public RegistrationResponse register(RegistrationRequest request);
    User save(User user); // нужен для регистрации!
}
