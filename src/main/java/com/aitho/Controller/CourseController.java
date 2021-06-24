package com.aitho.Controller;

import com.aitho.Models.Course;
import com.aitho.Models.Students;
import com.aitho.Repository.CourseRepository;
import com.aitho.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() { return courseService.getAllCourses(); }

    @GetMapping(path = "/courses/{name}",consumes = "application/json")
    public ResponseEntity<Course> findCourseByname(@PathVariable("name") String name) {
        return  courseService.findCourseByName(name);
    }

    @PostMapping(path = "/courses",consumes = "application/json")
    public ResponseEntity<Course> addCoruse(@RequestBody Course course) {
        return  courseService.addCourse(course);
    }

//    @PutMapping(path = "/courses/{id}",consumes = "application/json")
//    public ResponseEntity<Course> upgradeCourse (@PathVariable("id") String id,@RequestBody Course course) {
//        return courseService.updateCourse(id,course);
//    }
}
