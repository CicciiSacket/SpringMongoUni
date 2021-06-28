package com.aitho.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Students")
public class Students extends Person {
    @Id
    private String id;

    public Students(String name,String surname,String email,String password,String token) {
        super(name,surname,email,Role.Student,password,token);
    }
}