package com.aitho.Controller;

import com.aitho.Models.Course;
import com.aitho.Models.Teacher;
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

    @GetMapping("/course")
    public List<Course> getAllCourses() { return courseService.getAllCourses(); }

    @GetMapping(path = "/course/{name}",consumes = "application/json")
    public ResponseEntity<Course> findCourseByName(@PathVariable("name") String name) {
        return courseService.findCourseByName(name);
    }

    @PostMapping(path = "/course",consumes = "application/json")
    public ResponseEntity<Course> addCourse(@RequestBody Course course, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (checkController.checkLoginAdmin(email,role,token)){
            courseService.addCourse(course);
            return new ResponseEntity<>(null,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping(path = "/course/{id}",consumes = "application/json")
    public ResponseEntity<Course> updateCourse (@PathVariable("id") String id,@RequestBody Course course,@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (checkController.checkLoginTeacher(email,role,token)){
            courseService.updateCourse(id,course);
            return new ResponseEntity<>(null,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping(path = "/course",consumes = "application/json")
    public ResponseEntity<HttpStatus> deleteCourse(@RequestBody Course course, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (checkController.checkLoginAdmin(email,role,token)){
            courseService.deleteCourse(course);
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/course/search")
    public ResponseEntity<List<Course>> searchFromListID(@RequestHeader List<String> id) {
        return new ResponseEntity<>(courseService.getCoursesFromIdList(id), HttpStatus.OK);
    }
}
