package com.aitho.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;
@Getter
@Setter
@AllArgsConstructor
public class Person {
    @Field
    private String name;
    @Field
    private String surname;
    @Field
    private String email;
    @Field
    private Role role;
    protected enum Role { Admin, Teacher, Student }
    @Field
    private String password;
    @Field
    private String token;
}
