package com.aitho.Service;

import com.aitho.Models.Admin;
import com.aitho.Models.Students;
import com.aitho.Models.Teacher;
import com.aitho.Repository.AdminRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    public Boolean existAdminById(String id) {return adminRepository.existsById(id);}

    public Optional<Admin> searchAdminByEmail(@PathVariable("email") String email) {
        return adminRepository.findAdminByEmail(email);
    }

    public ResponseEntity<Admin> addAdmin (@RequestBody Admin admin) {
        if (adminRepository.findAdminByEmail(admin.getEmail()).isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            admin.setToken(JWT.create().withSubject(admin.getEmail()).sign(Algorithm.HMAC512(admin.getPassword())));
            Admin _admin = adminRepository.save(new Admin(admin.getName(), admin.getSurname(), admin.getEmail(), admin.getPassword(), admin.getToken()));
            return new ResponseEntity<>(_admin, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAdmin(@RequestBody Admin admin) {
        Optional<Admin> _admin = adminRepository.findById(admin.getId());
        if (_admin.isPresent()) {
            String adminId = _admin.get().getId();
            adminRepository.deleteById(adminId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") String id, @RequestBody Admin admin) {
        Optional<Admin> _admin = adminRepository.findById(id);
        if (_admin.isPresent()) {
            Admin _Admin = _admin.get();
            _Admin.setEmail(admin.getEmail());
            return new ResponseEntity<>(adminRepository.save(_Admin), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
