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
    private Integer maxCFU;
    @Field
    private Integer minCFU;

    public Course(String name, Integer maxCFU) {
        this.name = name;
        this.maxCFU = maxCFU;
        this.minCFU = 18;
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

    public Integer getmaxCFU() { return maxCFU;  }

    public void setmaxCFU(Integer CFU) { this.maxCFU = CFU; }

    public Integer getMinCFU() {
        return minCFU;
    }
}
