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

    @PostMapping(path = "/login/students",consumes = "application/json")
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

    @PostMapping(path = "/login/teachers",consumes = "application/json")
    public ResponseEntity<String> loginTeachers(@RequestBody Authentication auth) {
        JSONObject resp = new JSONObject();
        try {
            resp.put("token", loginService.loginTeacher(auth));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
    }

    @PostMapping(path = "/login/admin",consumes = "application/json")
    public ResponseEntity<String> loginAdmins(@RequestBody Authentication auth) {
        JSONObject resp = new JSONObject();
        try {
            resp.put("token", loginService.loginAdmin(auth));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
    }

}