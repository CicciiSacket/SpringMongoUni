package com.aitho.Controller;

import com.aitho.Repository.CourseRepository;
import com.aitho.Service.CourseService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
}
