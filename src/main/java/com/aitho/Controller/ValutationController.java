package com.aitho.Controller;

import com.aitho.Models.Students;
import com.aitho.Models.Teacher;
import com.aitho.Models.Valutation;
import com.aitho.Repository.ValutationRepository;
import com.aitho.Service.ValutationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ValutationController {
    private final ValutationService valutationService;

    @Autowired
    public ValutationController(ValutationService valutationService) {
        this.valutationService = valutationService;
    }

    @GetMapping("/valutations")
    public List<Valutation> getAllValutations() { return valutationService.getAllValutations(); }

    @PostMapping(path = "/valutations",consumes = "application/json")
    public ResponseEntity<Valutation> addValutation(@RequestBody Valutation valutation) {
        return valutationService.addValutation(valutation);
    }
}
