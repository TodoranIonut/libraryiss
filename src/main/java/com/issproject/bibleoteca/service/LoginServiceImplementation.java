package com.issproject.bibleoteca.service;

import com.issproject.bibleoteca.model.AuthProvider;
import com.issproject.bibleoteca.model.User;
import com.issproject.bibleoteca.repository.BookRepository;
import com.issproject.bibleoteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImplementation implements LoginService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User loginUser(String username, String password) {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .filter(user -> user.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User does not exist"));
    }

    @Override
    public User loginUser(AuthProvider authProvider) {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getUsername().equals(authProvider.getUsername()))
                .filter(user -> user.getPassword().equals(authProvider.getPassword()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User does not exist"));
    }
}
