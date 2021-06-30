package com.aitho.Service;

import com.aitho.Models.Students;
import com.aitho.Models.Valutation;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    private final StudentService studentsService;
    private final TeacherService teacherService;
    private final AdminService adminService;
    private final ValutationService valutationService;

    @Autowired
    public LoginService(StudentService studentsService, TeacherService teacherService, AdminService adminService, ValutationService valutationService) {
        this.studentsService = studentsService;
        this.teacherService = teacherService;
        this.adminService = adminService;
        this.valutationService = valutationService;
    }

    public String loginStudents(@RequestBody Students students ) {
        if(studentsService.existStudentById(students.getId()) == true) {
            Optional<Students> logged = studentsService.searchStudents(students.getId());
            if(students.getPassword().equals(logged.get().getPassword())){
                logged.get().setToken(JWT.create().withSubject(students.getEmail()).sign(Algorithm.HMAC512(students.getPassword())));
                return logged.get().getToken();
            }
        }
        return null;
    }
}
