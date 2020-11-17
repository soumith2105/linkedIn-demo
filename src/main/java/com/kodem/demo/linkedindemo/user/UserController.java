package com.kodem.demo.linkedindemo.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // AUTHENTICATION
    // @Autowired
    // private UserRepository applicationUserRepository;

    // public UserController(UserRepository applicationUserRepository) {
    //     this.applicationUserRepository = applicationUserRepository;
    // }

    // RETRIEVE
    @GetMapping("/{username}")
    public Optional<User> getUserInfo(@PathVariable String username) {
        return userService.getUser(username);
    }

    @GetMapping("/all")
    public List<User> loginMessage() {
        return userService.getAllUsers();
    }

    // UPDATE
    // @PutMapping("/{id}")
    // public void updateUser(@RequestBody User user) {
    //     userService.updateUser(user);
    // }

    // DELETE
    // @DeleteMapping("/{username}")
    // public void deleteUser(@PathVariable String username) {
    //     userService.deleteUser(username);
    // }

}