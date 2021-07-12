package com.aitho.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestForCourse {

    private Course course;
    private String mail;
}
