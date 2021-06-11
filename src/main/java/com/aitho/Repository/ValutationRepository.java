package com.aitho.Repository;

import com.aitho.Models.Valutation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ValutationRepository extends MongoRepository<Valutation,String > {

    @Query("{id_student : ?0}")
    List<Valutation> findStudentValutations(String id);

    @Query("{id_teacher : ?0}")
    List<Valutation> findTeacherValutations(String id);

}
