package com.aitho.Controller;

import com.aitho.Models.Course;
import com.aitho.Models.Teacher;
import com.aitho.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() { return teacherService.getAllTeachers(); }

    @GetMapping("/teacherstest")
    public ResponseEntity<Stream<Teacher>> test(@RequestHeader List<String> id) {
        return new ResponseEntity<>(teacherService.teachersById(id), HttpStatus.OK);
    }

   @PostMapping(path = "/teachers",consumes = "application/json")
   public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
      return teacherService.addTeacher(teacher);
   }

    @PutMapping(path = "/teachers/{id}",consumes = "application/json")
    public ResponseEntity<Teacher> upgradeTeachers(@PathVariable("id") String id, @RequestBody Teacher teacher) {
        return teacherService.updateTeacher(id,teacher);
    }

    @DeleteMapping(path = "/teachers",consumes = "application/json")
    public ResponseEntity<HttpStatus> deleteTeacher(@RequestBody Teacher teacher) {
        return teacherService.deleteTeacher(teacher);
    }
}
