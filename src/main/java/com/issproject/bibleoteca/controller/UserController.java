package com.issproject.bibleoteca.controller;

import com.issproject.bibleoteca.model.User;
import com.issproject.bibleoteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path ="/{userId}")
    public User getUser(@PathVariable int userId){
        return userService.findAll()
                .stream()
                .filter(user -> userId == user.getId())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User " + userId + " not exist"));
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }

//    @GetMapping(path = "/subscriber")
//    public List<User> getAllSubscribers(){
//        return userService.findAll().stream()
//                .filter(user -> user.getRole().equals(SUBSCRIBER.name()))
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping(path = "/librarian")
//    public List<User> getAllLibrarians(){
//        return userService.findAll().stream()
//                .filter(user -> user.getRole().equals(LIBRARIAN.name()))
//                .collect(Collectors.toList());
//    }
//
//    @PostMapping(path = "/subscriber")
//    public String registerNewSubscriber(@RequestBody User user){
//        if(user.getRole().equals(SUBSCRIBER.name())) {
//            userService.registerNewUser(user);
//            return "New subscriber was registered";
//        }
//        return "The role is not SUBSCRIBER";
//    }
//
//    @PostMapping(path = "/librarian")
//    public String registerNewLibrarian(@RequestBody User user){
//        if(user.getRole().equals(LIBRARIAN.name())) {
//            userService.registerNewUser(user);
//            return "New librarian was registered";
//        }
//        return "The role is not LIBRARIAN";
//    }
//
//    @DeleteMapping(path = "/subscriber/{userId}")
//    public String deleteSubscriberUser(@PathVariable("userId") int userId){
//        return "Subscriber was deleted successfully";
//    }
//
//    @DeleteMapping(path = "/librarian/{userId}")
//    public String deleteLibrarianUser(@PathVariable("userId") int userId){
//        return "Librarian was deleted successfully";
//    }
//
//    @PutMapping(path = "/subscriber/{userId}")
//    public String updateSubscriberUser(@PathVariable("userId")int userId, User user){
//        return "Subscriber was updated successfully";
//    }
//
//    @PutMapping(path = "/librarian/{userId}")
//    public String updateLibrarianUser(@PathVariable("userId")int userId, User user){
//        return "Librarian was updated successfully";
//    }

}
