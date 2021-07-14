package com.aitho.Controller;

import com.aitho.Models.*;
import com.aitho.Repository.CourseRepository;
import com.aitho.Repository.StudentsRepository;
import com.aitho.Repository.TeacherRepository;
import com.aitho.Repository.ValutationRepository;
import com.aitho.Service.CheckController;
import com.aitho.Service.StudentService;
import com.aitho.Service.ValutationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ValutationController {

    private final ValutationService valutationService;
    private final ValutationRepository valutationRepository;
    private final StudentService studentService;
    private final StudentsRepository studentsRepository;
    private final CourseRepository courseRepository;
    private final CheckController checkController;
    private final TeacherRepository teacherRepository;

    @Autowired
    public ValutationController(ValutationService valutationService, ValutationRepository valutationRepository, StudentService studentService, StudentsRepository studentsRepository, CourseRepository courseRepository, CheckController checkController, TeacherRepository teacherRepository) {
        this.valutationService = valutationService;
        this.valutationRepository = valutationRepository;
        this.studentService = studentService;
        this.studentsRepository = studentsRepository;
        this.courseRepository = courseRepository;
        this.checkController = checkController;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/valutations") //Testata
    public ResponseEntity<List<Valutation>> getAllValutations(@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if ( checkController.checkLoginAdmin(email,role,token) ) {
            return new ResponseEntity<>(valutationService.getAllValutations(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/valutations/student") //Testata
    public ResponseEntity<List<Valutation>> getValutationsOfStudents(@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if( checkController.checkLoginStudent(email,role,token) ) {
            Optional<Students> authStudent = studentService.searchStudentByEmail(email);
            return authStudent.map(students -> new ResponseEntity<>(valutationService.getStudentValutations(students.getId()), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/valutations/teacher") //Testata
    public ResponseEntity<List<Valutation>> getValutationOfTeacher(@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if( checkController.checkLoginTeacher(email,role,token) ) {
            Optional<Teacher> authTeacher = teacherRepository.findTeacherByEmail(email);
            return authTeacher.map(teacher -> new ResponseEntity<>(valutationService.getTeacherValutations(teacher.getId()), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/valutations/course/{id}") //Testata
    public ResponseEntity<List<Valutation>> getValutationsOfCourse(@PathVariable("id") String idCourse,@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if( checkController.checkLoginTeacher(email,role,token) || checkController.checkLoginAdmin(email,role,token) ) {
            Optional<Course> authCourse = courseRepository.findCourseById(idCourse);
            return authCourse.map(course -> new ResponseEntity<>(valutationService.getCourseValutations(course.getId()), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping(path = "/valutations",consumes = "application/json", produces =  "application/json") //testata
    public ResponseEntity<HttpStatus> addValutation(@RequestBody Valutation valutation, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (valutationRepository.findByIdStudVal(valutation.getId_student()).isPresent() && valutationRepository.findByIdStudVal(valutation.getId_student()).get().getId_student().equals(valutation.getId_student())) { return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE); }
        if (courseRepository.existsById(valutation.getId_course()) || teacherRepository.existsById(valutation.getId_teacher()) || studentsRepository.existsById(valutation.getId_student())) {
            if( checkController.checkLoginTeacher(email,role,token) || checkController.checkLoginAdmin(email,role,token) ){
                valutationService.addValutation(valutation);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(path = "/valutation/{id}",consumes = "application/json")
    public ResponseEntity<Valutation> updateValutation (@PathVariable("id") String id, @RequestBody Valutation valutation) {
        return valutationService.updateValutation(id,valutation);
    }

    @DeleteMapping(path = "/valutation",consumes = "application/json")
    public ResponseEntity<HttpStatus> deleteValutation(@RequestBody Valutation valutation) {
        return valutationService.deleteValutation(valutation);
    }
}
