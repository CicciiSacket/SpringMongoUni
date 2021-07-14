package com.aitho.Controller;

import com.aitho.Models.*;
import com.aitho.Repository.TeacherRepository;
import com.aitho.Service.CheckController;
import com.aitho.Service.StudentService;
import com.aitho.Service.ValutationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ValutationController {

    private final ValutationService valutationService;
    private final StudentService studentService;
    private final CheckController checkController;
    private final TeacherRepository teacherRepository;

    @Autowired
    public ValutationController(ValutationService valutationService, StudentService studentService, CheckController checkController, TeacherRepository teacherRepository) {
        this.valutationService = valutationService;
        this.studentService = studentService;
        this.checkController = checkController;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/valutations") //Testata
    public ResponseEntity<List<Valutation>> getAllValutations(@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if ( checkController.checkLoginAdmin(email,role,token) ) {
            return new ResponseEntity<>(valutationService.getAllValutations(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/valutations/student") //Testata
    public ResponseEntity<List<Valutation>> getValutationsOfStudents(@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if( checkController.checkLoginStudent(email,role,token) ) {
            Optional<Students> authStudent = studentService.searchStudentByEmail(email);
            return authStudent.map(students -> new ResponseEntity<>(valutationService.getStudentValutations(students.getId()), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/valutations/teacher/{id}")
    public ResponseEntity<List<Valutation>> getTeacherValutations(@PathVariable("id") String id,@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (id.isEmpty()) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        if (teacherRepository.findById(id).isEmpty()) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
        if( checkController.checkLoginTeacher(email,role,token) ) {
            return new ResponseEntity<>(valutationService.getTeacherValutations(id),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/valutations/course/{id}")
    public List<Valutation> getCourseValutations(@PathVariable("id") String id) { return  valutationService.getCourseValutations(id); }

//    @PostMapping(path = "/valutations",consumes = "application/json")
//    public ResponseEntity<Valutation> addValutation(@RequestBody Valutation valutation) {
//
////        return valutationService.addValutation(valutation);
//    }

    @PutMapping(path = "/valutation/{id}",consumes = "application/json")
    public ResponseEntity<Valutation> updateValutation (@PathVariable("id") String id, @RequestBody Valutation valutation) {
        return valutationService.updateValutation(id,valutation);
    }

    @DeleteMapping(path = "/valutation",consumes = "application/json")
    public ResponseEntity<HttpStatus> deleteValutation(@RequestBody Valutation valutation) {
        return valutationService.deleteValutation(valutation);
    }
}
