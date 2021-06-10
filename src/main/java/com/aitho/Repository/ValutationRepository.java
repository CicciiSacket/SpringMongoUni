package com.aitho.Repository;

import com.aitho.Models.Valutation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ValutationRepository extends MongoRepository<Valutation,String > {
}
