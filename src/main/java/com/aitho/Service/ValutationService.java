package com.aitho.Service;

import com.aitho.Repository.ValutationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValutationService {

    private final ValutationRepository valutationRepository;

    @Autowired
    public ValutationService(ValutationRepository valutationRepository) {
        this.valutationRepository = valutationRepository;
    }



}
