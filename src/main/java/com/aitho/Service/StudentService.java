package com.aitho.Service;

import com.aitho.Models.Students;
import com.aitho.Repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentsRepository studentsRepository;

    @Autowired
    public StudentService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public List<Students> getAllStudents() { return studentsRepository.findAll(); }

    public ResponseEntity<Students> addStudents(@RequestBody Students students) {
        try {
            //inserire controllo mail
            //System.out.println("\n post mapping \n ");
            Students _students = studentsRepository.save(new Students(students.getName(),students.getSurname(),students.getEmail()));
            return new ResponseEntity<>(_students, HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Students> updateStudents(@PathVariable("id") String id, @RequestBody Students students) {
        Optional<Students> _students = studentsRepository.findById(id);
        if (_students.isPresent()) {
            Students _student = _students.get();
            _student.setEmail(students.getEmail());
            return new ResponseEntity<>(studentsRepository.save(_student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteStudents(@RequestBody String id) {
        try {
            studentsRepository.deleteById(id);
            System.out.println(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
