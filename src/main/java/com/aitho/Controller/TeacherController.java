package com.aitho.Controller;

import com.aitho.Models.Teacher;
import com.aitho.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() { return teacherService.getAllTeachers(); }

    @PostMapping(path = "/teachers",consumes = "application/json")
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
       return teacherService.addTeacher(teacher);
    }
}
