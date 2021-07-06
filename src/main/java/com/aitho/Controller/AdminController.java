package com.aitho.Controller;

import com.aitho.Models.Admin;
import com.aitho.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAllAdmins (){
        return new ResponseEntity<>(adminService.getAllAdmin(), HttpStatus.OK);
    }

    @PostMapping(path ="/admin",consumes = "application/json")
    public ResponseEntity<Admin> addAdmin (@RequestBody Admin admin) {
        adminService.addAdmin(admin);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
    @DeleteMapping(path = "/admin",consumes = "application/json")
    public ResponseEntity<HttpStatus> deleteAdmin(@RequestBody Admin admin) {
        return adminService.deleteAdmin(admin);
    }
}
