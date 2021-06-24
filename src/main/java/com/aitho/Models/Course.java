package com.aitho.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "Courses")
public class Course {
    @Id
    private String id;
    @Field
    private String name;
    @Field
    private Integer CFU;

    public Course(String name, Integer CFU) {
        this.name = name;
        this.CFU = CFU;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCFU() { return CFU;  }

    public void setCFU(Integer CFU) { this.CFU = CFU; }

}
