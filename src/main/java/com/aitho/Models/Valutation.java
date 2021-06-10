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
    private Integer CFU;

    public Valutation(String id_course, String id_student,String id_teacher,Integer CFU) {
        this.CFU = CFU;
        this.id_course = id_course;
        this.id_student = id_student;
        this.id_teacher = id_teacher;
    }


    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getId_course() {
        return id_course;
    }

    public void setId_course(String id_course) {
        this.id_course = id_course;
    }

    public String getId_student() {
        return id_student;
    }

    public void setId_student(String id_student) {
        this.id_student = id_student;
    }

    public String getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(String id_teacher) {
        this.id_teacher = id_teacher;
    }

    public Integer getCFU() {
        return CFU;
    }

    public void setCFU(Integer CFU) {
        this.CFU = CFU;
    }
}
