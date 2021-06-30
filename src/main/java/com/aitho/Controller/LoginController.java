package com.aitho.Controller;

import com.aitho.Models.Students;
import com.aitho.Models.Valutation;
import com.aitho.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(path = "/login",consumes = "application/json")
    public String loginStudents(@RequestBody Students students ) {
        return loginService.loginStudents(students);
    }

    // @PostMapping(path = "/login/",consumes = "application/json")
    // public ResponseEntity<> loginTeachers(@RequestBody Teacher teachers ) { //login insegnante OK --> FE
    //     Boolean logged = teacherService.existsById(id);
    //     if(logged == true) {
    //         if(teachers.getPassword() == logged.getPassword()){
    //             return new ResponseEntity<>(null, HttpStatus.OK);
    //         }
    //     }
    //     return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    // } 

    // @PostMapping(path = "/login/admin",consumes = "application/json")
    // public ResponseEntity<> loginTeachers(@RequestBody Admin admin ) { //login ADMIN OK --> FE
    //     Boolean logged = adminService.existAdminById(id);
    //     if(logged == true){
    //         if(admin.getPassword() == logged.getPassword()){
    //             return new ResponseEntity<>(null, HttpStatus.OK);
    //         }
    //     }
    //     return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    // } 

}