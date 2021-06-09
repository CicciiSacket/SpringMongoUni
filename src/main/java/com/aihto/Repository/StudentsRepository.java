package com.aihto.Repository;

import com.aihto.Models.Students;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends MongoRepository< Students,String >{};