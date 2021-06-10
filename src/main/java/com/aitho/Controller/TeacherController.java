package com.aitho.Controller;

import com.aitho.Models.Students;
import com.aitho.Models.Teacher;
import com.aitho.Repository.StudentsRepository;
import com.aitho.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherController {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() { return teacherRepository.findAll(); }

    @PostMapping(path = "/teachers",consumes = "application/json")
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
        try {
            Teacher _teacher = teacherRepository.save(new Teacher(teacher.getName(),teacher.getSurname(),teacher.getEmail()));
            return new ResponseEntity<>(_teacher, HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
