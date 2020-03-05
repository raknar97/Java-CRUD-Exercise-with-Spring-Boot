package com.springcrud.springcrud.controllers;


import com.springcrud.springcrud.entities.User;
import com.springcrud.springcrud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("/users")
    List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }


    @DeleteMapping("/users/{id}")
    void deleteUserById(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }


}
