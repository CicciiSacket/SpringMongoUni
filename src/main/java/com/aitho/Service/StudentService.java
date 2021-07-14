package com.aitho.Service;

import com.aitho.Models.Course;
import com.aitho.Models.Students;
import com.aitho.Repository.StudentsRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void addStudent(@RequestBody Students students) {
        students.setToken(JWT.create().withSubject(students.getEmail()).sign(Algorithm.HMAC512(students.getPassword())));
        studentsRepository.save(new Students(students.getName(), students.getSurname(), students.getEmail(), students.getPassword(), students.getToken()));
    }

    public Optional<Students> searchStudent(@PathVariable("id") String id) {
        return studentsRepository.findById(id);
    }

    public Optional<Students> searchStudentByEmail(@PathVariable("email") String email) {
        return studentsRepository.findStudentByEmail(email);
    }

    public void updateStudentMail(@PathVariable("id") String id, @RequestBody String newMail) {
        Optional<Students> _students = studentsRepository.findById(id);
        if (_students.isPresent()) {
            Students _student = _students.get();
            _student.setEmail(newMail);
            studentsRepository.save(_student);
        }
    }

    public void deleteStudent(@RequestBody Students student) {
        Students students = studentsRepository.findStudentByEmail(student.getEmail()).get();
        studentsRepository.delete(students);
    }

    public List<Students> getStudentFromIdList (@RequestHeader List<String> studentsID ) {
        return studentsRepository.findAll().stream().filter(students -> studentsID.contains(students.getId())).collect(Collectors.toList());
    }
}

