package com.aitho.Service;

import com.aitho.Models.Admin;
import com.aitho.Models.Role;
import com.aitho.Models.Students;
import com.aitho.Models.Teacher;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@Service
public class CheckController {

    private final StudentService studentService;
    private final ValutationService valutationService;
    private final TeacherService teacherService;
    private final AdminService adminService;

    public CheckController(StudentService studentService, ValutationService valutationService, TeacherService teacherService, AdminService adminService) {
        this.studentService = studentService;
        this.valutationService = valutationService;
        this.teacherService = teacherService;
        this.adminService = adminService;
    }

    public boolean checkLoginStudents(@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (Role.valueOf(role) == Role.Student) {
            Optional<Students> authStudent = studentService.searchStudentByEmail(email);
            if (authStudent.isPresent() && authStudent.get().getToken().equals(token)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLoginAdmin(@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (Role.valueOf(role) == Role.Admin) {
            Optional<Admin> authAdmin = adminService.searchAdminByEmail(email);
            if (authAdmin.isPresent() && authAdmin.get().getToken().equals(token)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLoginTeacher(@RequestHeader(value="email") String email, @RequestHeader(value="role") String role, @RequestHeader(value="token") String token) {
        if (Role.valueOf(role) == Role.Teacher) {
            Optional<Teacher> authTeacher = teacherService.searchTeacherByEmail(email);
            if (authTeacher.isPresent() && authTeacher.get().getToken().equals(token)) {
                return true;
            }
        }
        return false;
    }
}
