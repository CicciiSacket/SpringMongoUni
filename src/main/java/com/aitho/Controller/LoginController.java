package com.aitho.Controller;

import com.aitho.Models.Authentication;
import com.aitho.Models.Role;
import com.aitho.Models.Students;
import com.aitho.Models.Valutation;
import com.aitho.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LoginController {

    private final LoginService loginService;;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(path = "/login",consumes = "application/json")
    public ResponseEntity<String> loginStudents(@RequestBody Authentication auth) {
        JSONObject resp = new JSONObject();
        try {
            resp.put("token", loginService.loginStudents(auth));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
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