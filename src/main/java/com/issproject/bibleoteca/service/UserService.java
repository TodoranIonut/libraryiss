package com.issproject.bibleoteca.service;

import com.issproject.bibleoteca.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers();
    User findUserById(int userId);
    void registerNewUser(User user);
    void deleteUser(int userId);
    User loginUser(String username, String password);
}
