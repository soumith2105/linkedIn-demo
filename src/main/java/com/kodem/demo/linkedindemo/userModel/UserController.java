package com.kodem.demo.linkedindemo.userModel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user") // Setting a prefix to all URI's below
public class UserController {

    @Autowired
    private UserService userService;

    // Retrieve (GET Request)

    // Get user object based on username
    @GetMapping("/{username}")
    public Optional<User> getUserInfo(@PathVariable String username) {
        return userService.getUser(username);
    }

    // Get all user objects
    @GetMapping("/all")
    public List<User> loginMessage() {
        return userService.getAllUsers();
    }

}