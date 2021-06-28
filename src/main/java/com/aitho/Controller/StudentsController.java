package com.aitho.Controller;

import com.aitho.Models.Students;
import com.aitho.Repository.StudentsRepository;
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
    private final StudentsRepository studentsRepository;

    @Autowired
    public StudentsController(StudentService studentService, StudentsRepository studentsRepository) {
        this.studentService = studentService;
        this.studentsRepository = studentsRepository;
    }

    @GetMapping("/students")
    public List<Students> getAllStudents() { return studentService.getAllStudents(); }

    @GetMapping("/students/{id}")
    public Optional<Students> searchStudent(@PathVariable("id") String id) { return studentService.searchStudents(id);}

    @PostMapping(path = "/students",consumes = "application/json")
    public ResponseEntity<Students> addStudents(@RequestBody Students students) {
        if(studentsRepository.findStudentByEmail(students.getEmail()).isPresent()){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
