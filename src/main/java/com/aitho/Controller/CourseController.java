package com.aitho.Controller;

import com.aitho.Repository.CourseRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
}
