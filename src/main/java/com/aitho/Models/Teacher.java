package com.aitho.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "Teachers")
public class Teacher extends Person {

    @Id
    private String id;

    public Teacher(String name,String surname,String email,String password,String token) {
        super(name,surname,email,Role.Teacher,password,token);
    }
}
