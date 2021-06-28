package com.aitho.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "Valutations")
public class Valutation {
    @Id
    private String id;
    @Field
    private String id_course;
    @Field
    private String id_student;
    @Field
    private String id_teacher;
    @Field
    private Integer vote;

    public Valutation(String id_course, String id_student, String id_teacher, Integer vote) {
        this.id_course = id_course;
        this.id_student = id_student;
        this.id_teacher = id_teacher;
        this.vote = vote;
    }
}
