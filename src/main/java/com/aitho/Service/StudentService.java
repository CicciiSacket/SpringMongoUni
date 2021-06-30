package com.aitho.Service;

import com.aitho.Models.Students;
import com.aitho.Repository.StudentsRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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

    public List<Students> getAllStudents() {
        return studentsRepository.findAll();
    }

    public Boolean existStudentById(String id) { return studentsRepository.existsById(id); }

    public ResponseEntity<Students> addStudents(@RequestBody Students students) {
        try {
            students.setToken(JWT.create().withSubject(students.getEmail()).sign(Algorithm.HMAC512(students.getPassword())));
            Students _students = studentsRepository.save(new Students(students.getName(), students.getSurname(), students.getEmail(), students.getPassword(), students.getToken()));
            return new ResponseEntity<>(_students, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<Students> searchStudents(@PathVariable("id") String id) {
        return studentsRepository.findById(id);
    }

    public Optional<Students> searchStudentByEmail(@PathVariable("email") String email) {
        return studentsRepository.findStudentByEmail(email);
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

    public ResponseEntity<HttpStatus> deleteStudents(@RequestBody Students student) {
        Optional<Students> _students = studentsRepository.findById(student.getId());
        if (_students.isPresent()) {
            String _student = _students.get().getId();
            studentsRepository.deleteById(_student);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

