package com.aitho.Repository;

import com.aitho.Models.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher,String> {

    @Query("{email : ?0}")
    Optional<Teacher> findTeacherByEmail(String email);


};
