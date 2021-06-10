package com.aitho.Repository;

import com.aitho.Models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course,String > {
}
