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

    @GetMapping("/teacher")
    public List<Teacher> getAllTeachers() { return teacherService.getAllTeachers(); }

    @GetMapping("/teacher/search")
    public ResponseEntity<List<Teacher>> searchFromListID(@RequestHeader List<String> id) {
        return new ResponseEntity<>(teacherService.getTeachersFromIdList(id), HttpStatus.OK);
    }

   @PostMapping(path = "/teacher",consumes = "application/json")
   public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
      return teacherService.addTeacher(teacher);
   }

    @PutMapping(path = "/teacher/{id}",consumes = "application/json")
    public ResponseEntity<Teacher> upgradeTeachers(@PathVariable("id") String id, @RequestBody Teacher teacher) {
        return teacherService.updateTeacher(id,teacher);
    }

    @DeleteMapping(path = "/teacher",consumes = "application/json")
    public ResponseEntity<HttpStatus> deleteTeacher(@RequestBody Teacher teacher) {
        return teacherService.deleteTeacher(teacher);
    }
}
