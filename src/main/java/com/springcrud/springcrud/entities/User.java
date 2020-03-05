package com.springcrud.springcrud.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.util.*;

@Entity
@Getter//Used lombok to avoid getters an setters generation
@Setter
@NoArgsConstructor//no permite parametros por constructor,por lo tanto es VACIO
//link validaciones posibles
//https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//es como el auto-increment de MySql
    private Integer id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @PastOrPresent(message = "The birthday date cannot be in the future")
    private Date birthdate;

    public User(Integer id, String name, Date birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }
}

