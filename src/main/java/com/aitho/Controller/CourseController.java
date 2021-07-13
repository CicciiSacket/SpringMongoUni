package com.aitho.Controller;

import com.aitho.Models.Course;
import com.aitho.Models.RequestForCourse;
import com.aitho.Repository.CourseRepository;
import com.aitho.Repository.StudentsRepository;
import com.aitho.Repository.TeacherRepository;
import com.aitho.Service.CheckController;
import com.aitho.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CourseController {

    private final CourseService courseService;
    private final CourseRepository courseRepository;
    private final CheckController checkController;
    private final StudentsRepository studentsRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public CourseController(CourseService courseService, CourseRepository courseRepository, CheckController checkController, StudentsRepository studentsRepository, TeacherRepository teacherRepository) {
        this.courseService = courseService;
        this.courseRepository = courseRepository;
        this.checkController = checkController;
        this.studentsRepository = studentsRepository;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/course")
    public ResponseEntity<List<Course>> getAllCourses(@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (checkController.checkLoginStudent(email,role,token) || checkController.checkLoginAdmin(email,role,token) || checkController.checkLoginTeacher(email,role,token)) {
            return new ResponseEntity<>(courseService.getAllCourses(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/course/search")
    public ResponseEntity<List<Course>> searchFromListID(@RequestHeader List<String> id) {
        return new ResponseEntity<>(courseService.getCoursesFromIdList(id), HttpStatus.OK);
    }

    @GetMapping(path = "/course/{id}",consumes = "application/json")
    public ResponseEntity<Course> searchCourse(@PathVariable("id") String id,@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (checkController.checkLoginStudent(email,role,token) || checkController.checkLoginAdmin(email,role,token) || checkController.checkLoginTeacher(email,role,token)) {
            if (!courseRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(courseService.findCourseById(id),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping(path = "/course",consumes = "application/json")
    public ResponseEntity<HttpStatus> addCourse(@RequestBody Course course, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (course.getName().isEmpty()) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        if (checkController.checkLoginAdmin(email,role,token)) {
            if(courseRepository.findCourseByName(course.getName()).isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            courseService.addCourse(course);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping(path = "/course/{id}",consumes = "application/json")
    public ResponseEntity<HttpStatus> updateCourseCFU (@PathVariable("id") String id,@RequestBody Integer newCFU,@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (newCFU <= 0) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        if (checkController.checkLoginAdmin(email,role,token) || checkController.checkLoginTeacher(email,role,token)) {
            courseService.updateCourseCFU(id,newCFU);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping(path = "/course",consumes = "application/json",produces = "application/json") //DA TESTARE
    public ResponseEntity<HttpStatus> deleteCourse(@RequestBody String courseName, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (courseName.isEmpty()) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        if (checkController.checkLoginAdmin(email,role,token)) {
            if(courseRepository.findCourseByName(courseName).isPresent()) {
                courseService.deleteCourse(courseName);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping(path = "/course/student/{nameCourse}")
    public ResponseEntity<ArrayList<String>> getStudentsCourse(@PathVariable("nameCourse") String nameCourse, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (nameCourse.isEmpty()) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        if (courseRepository.findCourseByName(nameCourse).isEmpty()) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
        if (checkController.checkLoginTeacher(email,role,token) || checkController.checkLoginAdmin(email,role,token)) {
            return new ResponseEntity<>(courseService.getStudentsCourse(nameCourse),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping(path = "/course/teacher/{nameCourse}",consumes = "application/json")
    public ResponseEntity<ArrayList<String>> getTeachersCourse(@PathVariable("nameCourse") String nameCourse, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (nameCourse.isEmpty()) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        if (courseRepository.findCourseByName(nameCourse).isEmpty()) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
        if (checkController.checkLoginAdmin(email,role,token)) {
            return new ResponseEntity<>(courseService.getTeachersCourse(nameCourse), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping(path = "/course/student",consumes = "application/json",produces = "application/json")
    public ResponseEntity<HttpStatus> addStudentInCourse(@RequestBody RequestForCourse requestForCourse, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (requestForCourse.getCourseName().isEmpty() || requestForCourse.getMail().isEmpty()) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        if (courseRepository.findCourseById(requestForCourse.getCourseName()).isEmpty() || studentsRepository.findStudentByEmail(requestForCourse.getMail()).isEmpty()) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
        if (checkController.checkLoginAdmin(email,role,token)) {
            courseService.addStudentInCourse(requestForCourse);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping(path = "/course/student",consumes = "application/json")
    public ResponseEntity<HttpStatus> deleteStudentInCourse(@RequestBody RequestForCourse requestForCourse,@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (requestForCourse.getCourseName().isEmpty() || requestForCourse.getMail().isEmpty()) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        if (courseRepository.findCourseByName(requestForCourse.getCourseName()).isEmpty() || studentsRepository.findStudentByEmail(requestForCourse.getMail()).isEmpty()) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
        if (checkController.checkLoginAdmin(email,role,token)) {
            courseService.deleteStudentInCourse(requestForCourse);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping(path = "/course/teacher",consumes = "application/json")
    public ResponseEntity<HttpStatus> addTeacherInCourse(@RequestBody RequestForCourse requestForCourse ,@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (requestForCourse.getCourseName().isEmpty() || requestForCourse.getMail().isEmpty()) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        if (courseRepository.findCourseByName(requestForCourse.getCourseName()).isEmpty() || teacherRepository.findTeacherByEmail(requestForCourse.getMail()).isEmpty()) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
        if (checkController.checkLoginAdmin(email,role,token)) {
            courseService.addTeacherInCourse(requestForCourse);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping(path = "/course/teacher",consumes = "application/json")
    public ResponseEntity<HttpStatus> deleteTeacherInCourse(@RequestBody RequestForCourse requestForCourse,@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (requestForCourse.getCourseName().isEmpty() || requestForCourse.getMail().isEmpty()) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        if (courseRepository.findCourseByName(requestForCourse.getCourseName()).isEmpty() || teacherRepository.findTeacherByEmail(requestForCourse.getMail()).isEmpty()) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
        if (checkController.checkLoginAdmin(email,role,token)) {
            courseService.deleteTeacherInCourse(requestForCourse);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping(path = "/course/teacher/bymail",consumes = "application/json")
    public ResponseEntity<List<Course>> getCoursesFromTeacherEmail(@RequestHeader(value="queryEmail") String queryEmail, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
       if (queryEmail.isEmpty()) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
       if (teacherRepository.findTeacherByEmail(queryEmail).isEmpty()) { return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        if (checkController.checkLoginAdmin(email,role,token) || checkController.checkLoginTeacher(email,role,token)) {
            return new ResponseEntity<>(courseService.searchCoursesOfTeachers(queryEmail),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
