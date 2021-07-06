package com.aitho.Controller;

import com.aitho.Models.Students;
import com.aitho.Repository.StudentsRepository;
import com.aitho.Service.AdminService;
import com.aitho.Service.CheckController;
import com.aitho.Service.StudentService;
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

    @GetMapping("/students")
    public ResponseEntity<List<Students>> getAllStudents(@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (checkController.checkLoginTeacher(email,role,token)) {
            return new ResponseEntity<>(studentService.getAllStudents(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Optional<Students>> searchStudent(@PathVariable("id") String id, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (checkController.checkLoginTeacher(email,role,token)) {
            return new ResponseEntity<>(studentService.searchStudents(id),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping(path = "/students",consumes = "application/json")
    public ResponseEntity<Students> addStudents(@RequestBody Students students, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (checkController.checkLoginAdmin(email,role,token)) {
            studentService.addStudents(students);
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping(path = "/students/{id}",consumes = "application/json")
    public ResponseEntity<Students> updateStudents(@PathVariable("id") String id, @RequestBody Students students) {
        return  studentService.updateStudents(id,students);
    }

    @DeleteMapping(path = "/students",consumes = "application/json")
    public ResponseEntity<HttpStatus> deleteStudents(@RequestBody Students student) {
        return studentService.deleteStudents(student);
    }
}
