package com.aitho.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@Data
@Document(collection = "Courses")
public class Course {
    @Id
    private String id;
    @Field
    private String name;
    @Field
    private Integer CFU;
    @Field
    private ArrayList<String> studentsId;
    @Field
    private ArrayList<String> teachersId;

    public Course(String name, Integer CFU) {
        this.name = name;
        this.CFU = CFU;
        this.studentsId = new ArrayList<>();
        this.teachersId = new ArrayList<>();
    }
}
