package com.aitho.Repository;

import com.aitho.Models.Course;
import com.aitho.Models.Students;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

@Repository
public interface CourseRepository extends MongoRepository<Course,String > {

    @Query("{name : ?0}")
    Optional<Course> findCourseByName(String name);

}
