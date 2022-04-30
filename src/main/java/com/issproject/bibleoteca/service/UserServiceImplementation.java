package com.issproject.bibleoteca.service;

import com.issproject.bibleoteca.model.User;
import com.issproject.bibleoteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public String loginUserWithRole(String username, String password) {
        return null;
    }

    @Override
    public void registerNewUser(User user) {
        System.out.println(user);
    }

    @Override
    public void deleteUser(int userId) {
        System.out.println(userId);
    }

    @Override
    public void updateUser(int userId, User user) {
        System.out.println(String.format("%s %s", user, user));
    }

    @Override
    public User findUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
