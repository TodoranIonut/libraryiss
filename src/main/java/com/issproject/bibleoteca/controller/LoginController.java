package com.issproject.bibleoteca.controller;

import com.issproject.bibleoteca.model.AuthProvider;
import com.issproject.bibleoteca.model.User;
import com.issproject.bibleoteca.service.LoginService;
import com.issproject.bibleoteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(path = "login")
    public User loginUser(@RequestHeader("username") String username,
                          @RequestHeader("password") String password) {
        return loginService.loginUser(username, password);
    }

    @PostMapping( "login2")
    public User loginUser(@RequestBody AuthProvider authProvider) {
        return loginService.loginUser(authProvider);
    }
}
