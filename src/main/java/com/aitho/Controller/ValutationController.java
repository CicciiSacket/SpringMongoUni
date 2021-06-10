package com.aitho.Controller;

import com.aitho.Models.Students;
import com.aitho.Repository.ValutationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValutationController {
    private final ValutationRepository valutationRepository;

    public ValutationController(ValutationRepository valutationRepository) {
        this.valutationRepository = valutationRepository;
    }
}
