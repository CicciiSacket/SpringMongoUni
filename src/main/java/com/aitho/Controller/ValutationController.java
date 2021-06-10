package com.aitho.Controller;

import com.aitho.Models.Students;
import com.aitho.Repository.ValutationRepository;
import com.aitho.Service.ValutationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValutationController {
    private final ValutationService valutationService;

    public ValutationController(ValutationService valutationService) {
        this.valutationService = valutationService;
    }
}
