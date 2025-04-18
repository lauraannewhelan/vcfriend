package com.vcfriend.backend.controller;

import com.vcfriend.backend.model.Individual;
import com.vcfriend.backend.repository.IndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/individuals")
@CrossOrigin(origins = "*")
public class IndividualController {

    @Autowired
    private IndividualRepository individualRepository;

    @GetMapping
    public List<Individual> getAllIndividuals() {
        return individualRepository.findAll();
    }

    @GetMapping("/{id}")
    public Individual getIndividualById(@PathVariable Long id) {
        return individualRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Individual not found with id: " + id));
    }
}
