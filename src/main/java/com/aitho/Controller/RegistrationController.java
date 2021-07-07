package com.aitho.Controller;

import com.aitho.Models.Students;
import com.aitho.Models.Teacher;
import com.aitho.Repository.StudentsRepository;
import com.aitho.Service.StudentService;
import com.aitho.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final StudentsRepository studentsRepository;

    @Autowired
    public RegistrationController(StudentService studentService, TeacherService teacherService, StudentsRepository studentsRepository) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.studentsRepository = studentsRepository;
    }

//    @PostMapping(path = "/registration/students",consumes = "application/json")
//    public ResponseEntity<Students> addStudents(@RequestBody Students students) {
//        if(studentsRepository.findStudentByEmail(students.getEmail()).isPresent()){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return  studentService.addStudents(students);
//    }

    @PostMapping(path = "/registration/teachers",consumes = "application/json")
   public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
      return teacherService.addTeacher(teacher);
   }



}
