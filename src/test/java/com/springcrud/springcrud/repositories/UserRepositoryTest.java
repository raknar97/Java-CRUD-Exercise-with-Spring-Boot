package com.springcrud.springcrud.repositories;

import com.springcrud.springcrud.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;


@DataJpaTest
class UserRepositoryTest {

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

    @Test
    void saveUserIntoDatabase() {
        Date birthdate = createDate(1997, 2, 4);
        User userCreated = createuser("Jorge", birthdate);
        assertThat(userCreated.getName()).isEqualTo("Jorge");
    }

    @Test
    void getUserFromDatabase() {
        User user = createuser("Josefa", createDate(2000, 7, 30));
        Optional<User> found = userRepository.findById(user.getId());
        assertThat(found.isPresent()).isTrue();
    }

    @Test
    void deleteUserFromDatabase() throws IllegalArgumentException {
        User user = createuser("Josefa", createDate(2000, 7, 30));
        assertThatCode(() -> {
            userRepository.deleteById(user.getId());
        }).doesNotThrowAnyException();
    }

    @Test
    void updateUserFromDatabase() {
        User user = createuser("Josefa", createDate(2000, 7, 30));
        Optional<User> found = userRepository.findById(user.getId());
        assertThat(found.isPresent()).isTrue();
        found.get().setName("Pepe");
        userRepository.save(found.get());
        Optional<User> userUpdated = userRepository.findById(user.getId());
        assertThat(userUpdated.get().getName()).isEqualTo("Pepe");
    }

    @Test
    void getAllUsersFromDatabase() {
        createuser("Josefa", createDate(2000, 7, 30));
        List<User> usersFound = userRepository.findAll();
        assertThat(usersFound.isEmpty()).isFalse();
    }
}