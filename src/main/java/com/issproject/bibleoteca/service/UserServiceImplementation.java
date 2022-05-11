package com.issproject.bibleoteca.service;

import com.issproject.bibleoteca.model.User;
import com.issproject.bibleoteca.repository.UserRepository;
import com.issproject.bibleoteca.security.ApplicationUserRole;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService {

    @Autowired
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if(user == null){
//            log.error("User not found in database");
//            throw new UsernameNotFoundException("User not found in database");
//        } else {
//            log.info("User found in database : {}" , username);
//        }
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(user.getRole()));
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
//    }

    @Override
    public List<User> getUsers() {
        log.info("Get all users");
        return userRepository.findAll();
    }

    @Override
    public void registerNewUser(User user) {
        log.info("Save user {} to database",user.getUsername());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int userId) {
        log.info("Delete user id {}",userId);
        userRepository.deleteById(userId);
    }

    @Override
    public User findUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User loginUser(String username, String password) {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .filter(user -> user.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User does not exist"));
    }
//    @Override
//    public User getUser(String username) {
//        return userRepository.findByUsername(username);
//    }
}
