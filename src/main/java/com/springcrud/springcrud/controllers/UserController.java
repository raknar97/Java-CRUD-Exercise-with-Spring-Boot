package com.springcrud.springcrud.controllers;


import com.springcrud.springcrud.entities.User;
import com.springcrud.springcrud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Integer id) {
        return userRepository.findById(id).orElse(null);
    }


}
