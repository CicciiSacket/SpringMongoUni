package com.aitho.Controller;

import com.aitho.Models.Teacher;
import com.aitho.Models.TeacherRes;
import com.aitho.Repository.TeacherRepository;
import com.aitho.Service.CheckController;
import com.aitho.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TeacherController {

    private final TeacherService teacherService;
    private final CheckController checkController;
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherController(TeacherService teacherService, CheckController checkController, TeacherRepository teacherRepository) {
        this.teacherService = teacherService;
        this.checkController = checkController;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/teacher")
    public ResponseEntity<List<Teacher>> getAllTeachers(@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (checkController.checkLoginStudent(email,role,token) || checkController.checkLoginAdmin(email,role,token)) {
            return new ResponseEntity<>(teacherService.getAllTeachers(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/teacher/search")
    public ResponseEntity<Map<String, TeacherRes>> getTeacherFromListID(@RequestHeader List<String> id) {
        return new ResponseEntity<>(teacherService.getTeachersFromIdList(id), HttpStatus.OK);
    }

   @PostMapping(path = "/teacher",consumes = "application/json")
   public ResponseEntity<HttpStatus>  addTeacher(@RequestBody Teacher teacher, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
       if (teacher.getEmail().isEmpty()) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
       if (checkController.checkLoginAdmin(email,role,token)) {
           if(teacherRepository.findTeacherByEmail(teacher.getEmail()).isPresent()){
               return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
           }
           teacherService.addTeacher(teacher);
           return new ResponseEntity<>(HttpStatus.CREATED);
       }
       return new ResponseEntity<>(HttpStatus.FORBIDDEN);
   }

    @PutMapping(path = "/teacher/{id}",consumes = "application/json")
    public ResponseEntity<HttpStatus>  updateTeachers(@PathVariable("id") String teachersID, @RequestBody String newMail, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (newMail.isEmpty()) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        if (teacherRepository.findTeacherByEmail(newMail).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if (checkController.checkLoginAdmin(email,role,token)) {
            teacherService.updateTeachertMail(teachersID,newMail);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping(path = "/teacher",consumes = "application/json")
    public ResponseEntity<HttpStatus> deleteTeacher(@RequestBody Teacher teacher, @RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (teacher.getEmail().isEmpty()) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        if (checkController.checkLoginAdmin(email,role,token)) {
            if(teacherRepository.findTeacherByEmail(teacher.getEmail()).isPresent()) {
                teacherService.deleteTeacher(teacher);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
