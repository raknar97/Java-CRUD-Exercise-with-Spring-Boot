package com.springcrud.springcrud.controllers;


import com.springcrud.springcrud.entities.User;
import com.springcrud.springcrud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    //GET
    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("/users")
    List<User> getUsers() {
        return userRepository.findAll();
    }

    //POST
    @PostMapping("/users")
    User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }


    //PUT
    @PutMapping("/users")
    User updateUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }


    //DELETE
    @DeleteMapping("/users/{id}")
    void deleteUserById(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }


}
