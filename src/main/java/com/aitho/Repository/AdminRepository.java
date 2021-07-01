package com.aitho.Repository;

import com.aitho.Models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends MongoRepository<Admin,String> {

    @Query("{email : ?0}")
    Optional<Admin> findAdminByEmail(String email);

};