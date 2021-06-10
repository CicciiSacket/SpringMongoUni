package com.aitho.Repository;

import com.aitho.Models.Students;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends MongoRepository< Students,String >{};