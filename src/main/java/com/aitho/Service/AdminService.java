package com.aitho.Service;

import com.aitho.Models.Admin;
import com.aitho.Models.Students;
import com.aitho.Models.Teacher;
import com.aitho.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Boolean existAdminById(String id) {return adminRepository.existsById(id);}

    public Optional<Admin> searchAdminByEmail(@PathVariable("email") String email) {
        return adminRepository.findAdminByEmail(email);
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
