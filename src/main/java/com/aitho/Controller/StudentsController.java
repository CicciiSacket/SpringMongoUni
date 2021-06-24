package com.aitho.Controller;

import com.aitho.Models.Students;
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

    @Autowired
    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Students> getAllStudents() { return studentService.getAllStudents(); }

    @GetMapping("/students/{id}")
    public Optional<Students> searchStudent(@PathVariable("id") String id) { return studentService.searchStudents(id);}

    @PostMapping(path = "/students",consumes = "application/json")
    public ResponseEntity<Students> addStudents(@RequestBody Students students) {
        return  studentService.addStudents(students);
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
