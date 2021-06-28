package com.aitho.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Person {

    private final String name;
    private final String surname;
    private final String email;
    private final Role role;
    protected enum Role { Admin, Teacher, Student }
    private final String password;
    private final String token;

    public Person(String name, String surname, String email, Role role, String password, String token) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        Role.valueOf(String.valueOf(role));
        this.password = password;
        this.token = token;
    }

}
