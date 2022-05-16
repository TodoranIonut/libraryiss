package com.issproject.bibleoteca.controller;

import com.issproject.bibleoteca.jwt.JwtConfig;
import com.issproject.bibleoteca.jwt.UsernameAndPasswordAuthenticationRequest;
import com.issproject.bibleoteca.model.User;
import com.issproject.bibleoteca.security.ApplicationUserRole;
import com.issproject.bibleoteca.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;

//@CrossOrigin
@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public LoginController(JwtConfig jwtConfig, SecretKey secretKey) {
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @GetMapping(path = "login")
    public User loginUser(@RequestHeader("username") String username,
                          @RequestHeader("password") String password) {
        return userService.loginUser(username, password);
    }

    @PostMapping(path ="login2")
    public ResponseEntity<String> getUser(@RequestBody UsernameAndPasswordAuthenticationRequest credentials){
        User loginUser = userService.getUsers()
                .stream()
                .filter(user -> credentials.getUsername().equals(user.getUsername()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User " + credentials.getUsername() + " not exist"));

        String token = Jwts.builder()
                .setSubject(loginUser.getUsername())
                .claim("authorities",  ApplicationUserRole.valueOf(loginUser.getRole()).getGrantedAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();

        return ResponseEntity.ok()
                .header(jwtConfig.getAuthorizationHeader(),jwtConfig.getTokenPrefix() + token)
                .body(jwtConfig.getTokenPrefix() + token);
    }
}
