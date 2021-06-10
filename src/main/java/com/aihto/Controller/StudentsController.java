package com.aihto.Controller;

import com.aihto.Models.Students;
import com.aihto.Repository.StudentsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class StudentsController {

    private final StudentsRepository studentsRepository;

    @Autowired
    public StudentsController(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @GetMapping("/students")
    public List<Students> getAllStudents() {
        return studentsRepository.findAll();
    }

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

    @PutMapping(path = "/students/{id}",consumes = "application/json")
    public ResponseEntity<Students> updateStudents(@PathVariable("id") String id, @RequestBody Students students) {
        Optional<Students> _students = studentsRepository.findById(id);
        System.out.println("\n" + _students + "\n");
        if (_students.isPresent()) {
            System.out.println("\n YY \n");
            Students _student = _students.get();
            _student.setEmail(students.getEmail());
            return new ResponseEntity<>(studentsRepository.save(_student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
