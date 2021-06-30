package com.aitho.Service;

import com.aitho.Models.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final AdminService adminService;
    private final ValutationService valutationService;

    @Autowired
    public LoginService(StudentService studentsService, TeacherService teacherService, AdminService adminService, ValutationService valutationService) {
        this.studentService = studentsService;
        this.teacherService = teacherService;
        this.adminService = adminService;
        this.valutationService = valutationService;
    }

    public String loginStudents(@RequestBody Authentication auth) {
        if (auth.getRole() == Role.Student){
            Optional<Students> loginStudent = studentService.searchStudentByEmail(auth.getEmail());
            if(loginStudent.isPresent() && loginStudent.get().getPassword().equals(auth.getPassword())) {
                loginStudent.get().setToken(JWT.create().withSubject(auth.getEmail()).sign(Algorithm.HMAC512(auth.getPassword())));
                studentService.updateStudents(loginStudent.get().getId(),loginStudent.get());
                return loginStudent.get().getToken();
            }
        }
        return null;
    }
}
