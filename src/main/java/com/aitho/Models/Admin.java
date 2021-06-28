package com.aitho.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Admins")
public class Admin extends Person {

    @Id
    private String id;

    public Admin(String name, String surname, String email, String password, String token) {
        super(name,surname,email,Role.Admin,password,token);
    }
}