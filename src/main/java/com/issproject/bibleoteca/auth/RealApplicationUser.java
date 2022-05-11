package com.issproject.bibleoteca.auth;

import com.google.common.collect.Lists;
import com.issproject.bibleoteca.model.User;
import com.issproject.bibleoteca.repository.UserRepository;
import com.issproject.bibleoteca.security.ApplicationUserPermission;
import com.issproject.bibleoteca.security.ApplicationUserRole;
import com.issproject.bibleoteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

import static com.issproject.bibleoteca.security.ApplicationUserRole.*;

@Repository("real")
public class RealApplicationUser implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public RealApplicationUser(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ApplicationUser selectApplicationUserByUsername(String username) {
        User realUser = userRepository.findAll()
                .stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst()
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", username)));

        return new ApplicationUser(
                realUser.getUsername(),
                passwordEncoder.encode(realUser.getPassword()),
                ApplicationUserRole.valueOf(realUser.getRole()).getGrantedAuthorities(),
                true,
                true,
                true,
                true
        );
    }


}
