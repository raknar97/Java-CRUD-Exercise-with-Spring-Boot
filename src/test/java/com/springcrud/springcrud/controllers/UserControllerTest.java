package com.springcrud.springcrud.controllers;

import com.springcrud.springcrud.entities.User;
import com.springcrud.springcrud.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;//Con MockMvc simulamos peticiones al controlador, como hacemos manualmente con POSTMAN

    @Autowired
    private UserRepository userRepository;


    User createuser(String name, Date birthdate) {
        User userToCreate = new User(null, name, birthdate);
        return userRepository.save(userToCreate);
    }

    Date createDate(int year, int month, int date) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date);
        return cal.getTime();

    }

    @BeforeEach
    void createUserForTests() {
        createuser("Josefa", createDate(2000, 7, 30));
    }

    @Test
    void getAllUsers() throws Exception {
        mvc.perform(get("/users")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void getUserById() throws Exception {

        mvc.perform(get("/users/{id}", 1)).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void deleteUserById() throws Exception {

        mvc.perform(delete("/users/{id}", 1)).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void createUser() throws Exception {
        mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "\t\"name\":\"Laura\",\n" +
                "\t\"birthdate\":\"2006-10-29\"\n" +
                "}")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void updateUser() throws Exception {
        mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "\t\"id\":1,\n" +
                "\t\"name\":\"Jersio\",\n" +
                "\t\"birthdate\":\"2005-10-29\"\n" +
                "}")).andExpect(status().isOk()).andDo(print());
    }
}