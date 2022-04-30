package com.issproject.bibleoteca.service;

import com.issproject.bibleoteca.model.AuthProvider;
import com.issproject.bibleoteca.model.User;

public interface LoginService {

    public User loginUser(String username, String password);
    public User loginUser(AuthProvider authProvider);
}
