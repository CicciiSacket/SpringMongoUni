package com.aihto.Repository;

import com.aihto.Models.Students;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentsRepository extends MongoRepository< Students,String >{

    @Query("{email : ?0}")
    Optional<Students> findStudentByEmail(String email);

};