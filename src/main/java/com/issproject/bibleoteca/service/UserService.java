package com.issproject.bibleoteca.service;

import com.issproject.bibleoteca.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> findAll();
    public User findUserById(int userId);
    public String loginUserWithRole(String username,String password);
    public void registerNewUser(User user);
    public void deleteUser(int userId);
    public void updateUser(int userId, User user);
}
