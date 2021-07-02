package com.aitho.Controller;

import com.aitho.Models.*;
import com.aitho.Repository.ValutationRepository;
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
    @Autowired
    public ValutationController(ValutationService valutationService, StudentService studentService) {
        this.valutationService = valutationService;
        this.studentService = studentService;
    }

    @GetMapping("/valutations")
    public List<Valutation> getAllValutations() { return valutationService.getAllValutations(); }

    @GetMapping("/valutations/student")
    public List<Valutation> getStudentValutations(@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if(Role.valueOf(role) == Role.Student) {
            Optional<Students> authStudent = studentService.searchStudentByEmail(email);
            if(authStudent.isPresent() && authStudent.get().getToken().equals(token)){
                return  valutationService.getStudentValutations(authStudent.get().getId());
            }
        }
        return  null;
    }

    @GetMapping("/valutations/teacher/{id}")
    public List<Valutation> getTeacherValutations(@PathVariable("id") String id) { return  valutationService.getTeacherValutations(id); }

    @GetMapping("/valutations/course/{id}")
    public List<Valutation> getCourseValutations(@PathVariable("id") String id) { return  valutationService.getCourseValutations(id); }

    @PostMapping(path = "/valutations",consumes = "application/json")
    public ResponseEntity<Valutation> addValutation(@RequestBody Valutation valutation) {
        return valutationService.addValutation(valutation);
    }

    @PutMapping(path = "/valutation/{id}",consumes = "application/json")
    public ResponseEntity<Valutation> upgradeCourse (@PathVariable("id") String id, @RequestBody Valutation valutation) {
        return valutationService.updateValutation(id,valutation);
    }

    @DeleteMapping(path = "/valutation",consumes = "application/json")
    public ResponseEntity<HttpStatus> deleteValutation(@RequestBody Valutation valutation) {
        return valutationService.deleteValutation(valutation);
    }
}
