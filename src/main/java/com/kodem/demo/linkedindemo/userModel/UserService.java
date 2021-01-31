package com.kodem.demo.linkedindemo.userModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create
    public void addUser(User user) {
        // Saving user object to DB
        userRepository.save(user);
    }

    // Retrieve

    // Getting all User objects based on username
    public Optional<User> getUser(String username) {
        return userRepository.findByUsername(username);
    }

    // Getting all User objects
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    // Update
    public void updateUser(User user) {
        userRepository.save(user);
    }

    // Delete
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

}
