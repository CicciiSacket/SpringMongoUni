package com.aitho.Repository;

import com.aitho.Models.Valutation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValutationRepository extends MongoRepository<Valutation,String> {

    @Query("{id_student : ?0}")
    List<Valutation> findStudentValutations(String id);

    @Query("{id_teacher : ?0}")
    List<Valutation> findTeacherValutations(String id);

    @Query("{id_course : ?0}")
    List<Valutation> findCourseValutations(String id);

}
