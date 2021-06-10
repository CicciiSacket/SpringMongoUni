package com.aitho.Controller;

import com.aitho.Models.Students;
import com.aitho.Repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class StudentsController {

    private final StudentsRepository studentsRepository;

    @Autowired
    public StudentsController(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @GetMapping("/students")
    public List<Students> getAllStudents() { return studentsRepository.findAll(); }

    @PostMapping(path = "/students",consumes = "application/json")
    public ResponseEntity<Students> addStudents(@RequestBody Students students) {
        try {
            //inserire controllo mail
            //System.out.println("\n post mapping \n ");
            Students _students = studentsRepository.save(new Students(students.getName(),students.getSurname(),students.getEmail()));
            return new ResponseEntity<>(_students,HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
