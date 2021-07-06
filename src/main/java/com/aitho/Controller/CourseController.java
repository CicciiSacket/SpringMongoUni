package com.aitho.Controller;

import com.aitho.Models.Course;
import com.aitho.Service.CheckController;
import com.aitho.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;
    private final CheckController checkController;

    @Autowired
    public CourseController(CourseService courseService, CheckController checkController) {
        this.courseService = courseService;
        this.checkController = checkController;
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() { return courseService.getAllCourses(); }

    @GetMapping(path = "/courses/{name}",consumes = "application/json")
    public ResponseEntity<Course> findCourseByname(@PathVariable("name") String name) {
        return courseService.findCourseByName(name);
    }

    @PostMapping(path = "/courses",consumes = "application/json")
    public ResponseEntity<Course> addCoruse(@RequestBody Course course, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (checkController.checkLoginAdmin(email,role,token)){
            courseService.addCourse(course);
            return new ResponseEntity<>(null,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping(path = "/courses/{id}",consumes = "application/json")
    public ResponseEntity<Course> upgradeCourse (@PathVariable("id") String id,@RequestBody Course course,@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (checkController.checkLoginTeacher(email,role,token)){
            courseService.updateCourse(id,course);
            return new ResponseEntity<>(null,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping(path = "/courses",consumes = "application/json")
    public ResponseEntity<HttpStatus> deleteCourse(@RequestBody Course course, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (checkController.checkLoginAdmin(email,role,token)){
            courseService.deleteCourse(course);
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
