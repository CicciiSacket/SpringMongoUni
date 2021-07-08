package com.aitho.Controller;

import com.aitho.Models.Students;
import com.aitho.Repository.StudentsRepository;
import com.aitho.Service.AdminService;
import com.aitho.Service.CheckController;
import com.aitho.Service.StudentService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class StudentsController {

    private final StudentService studentService;
    private final AdminService adminService;
    private final StudentsRepository studentsRepository;
    private final CheckController checkController;

    @Autowired
    public StudentsController(StudentService studentService, AdminService adminService, StudentsRepository studentsRepository, CheckController checkController) {
        this.studentService = studentService;
        this.adminService = adminService;
        this.studentsRepository = studentsRepository;
        this.checkController = checkController;
    }

    @GetMapping("/student")
    public ResponseEntity<List<Students>> getAllStudents(@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (checkController.checkLoginTeacher(email,role,token)) {
            return new ResponseEntity<>(studentService.getAllStudents(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Optional<Students>> searchStudent(@PathVariable("id") String id, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (checkController.checkLoginTeacher(email,role,token)) {
            if (!studentsRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(studentService.searchStudent(id),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping(path = "/student",consumes = "application/json")
    public ResponseEntity<Students> addStudents(@RequestBody Students students, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (students.getEmail().isEmpty()) { return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST); }
        if (checkController.checkLoginAdmin(email,role,token)) {
            if(studentsRepository.findStudentByEmail(students.getEmail()).isPresent()){
                return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
            }
            studentService.addStudent(students);
            return new ResponseEntity<>(null,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping(path = "/student/{id}",consumes = "application/json")
    public ResponseEntity<Students> updateStudentMail(@PathVariable("id") String id, @RequestBody String newMail,@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (newMail.isEmpty()) { return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST); }
        if (studentsRepository.findStudentByEmail(newMail).isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        if (checkController.checkLoginAdmin(email,role,token)) {
            studentService.updateStudentMail(id,newMail);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping(path = "/student",consumes = "application/json")
    public ResponseEntity<HttpStatus> deleteStudents(@RequestBody Students student, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (student.getEmail().isEmpty()) { return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST); }
        if (checkController.checkLoginAdmin(email,role,token)) {
            if(studentsRepository.findStudentByEmail(student.getEmail()).isPresent()) {
                studentService.deleteStudent(student);
                return new ResponseEntity<>(null,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
