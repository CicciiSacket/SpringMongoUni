package com.aitho.Controller;

import com.aitho.Models.Course;
import com.aitho.Repository.CourseRepository;
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
    private final CourseRepository courseRepository;
    private final CheckController checkController;

    @Autowired
    public CourseController(CourseService courseService, CourseRepository courseRepository, CheckController checkController) {
        this.courseService = courseService;
        this.courseRepository = courseRepository;
        this.checkController = checkController;
    }

    @GetMapping("/course")
    public ResponseEntity<List<Course>> getAllCourses(@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (checkController.checkLoginStudent(email,role,token)) {
            return new ResponseEntity<>(courseService.getAllCourses(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/course/search")
    public ResponseEntity<List<Course>> searchFromListID(@RequestHeader List<String> id) {
        return new ResponseEntity<>(courseService.getCoursesFromIdList(id), HttpStatus.OK);
    }

    @GetMapping(path = "/course/{id}",consumes = "application/json")
    public ResponseEntity<Course> searchCourse(@PathVariable("name") String id,@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (checkController.checkLoginStudent(email,role,token)) {
            if (!courseRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(courseService.findCourseById(id),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping(path = "/course",consumes = "application/json")
    public ResponseEntity<Course> addCourse(@RequestBody Course course, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (course.getName().isEmpty()) { return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST); }
        if (checkController.checkLoginAdmin(email,role,token)) {
            if(courseRepository.findCourseByName(course.getName()).isPresent()){
                return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
            }
            courseService.addCourse(course);
            return new ResponseEntity<>(null,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping(path = "/course/{id}",consumes = "application/json")
    public ResponseEntity<Course> updateCourseCFU (@PathVariable("id") String id,@RequestBody Integer newCFU,@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (newCFU <= 0) { return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST); }
        if (checkController.checkLoginAdmin(email,role,token)) {
            courseService.updateCourseCFU(id,newCFU);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping(path = "/course",consumes = "application/json")
    public ResponseEntity<HttpStatus> deleteCourse(@RequestBody Course course, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (course.getName().isEmpty()) { return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST); }
        if (checkController.checkLoginAdmin(email,role,token)) {
            if(courseRepository.findCourseByName(course.getName()).isPresent()) {
                courseService.deleteCourse(course);
                return new ResponseEntity<>(null,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    //aggiungere addStudentInCourse, deleteStudentInCourse, addteacherInCourse, deleteteacherInCourse, searchCoursesOfTeachers!
}
