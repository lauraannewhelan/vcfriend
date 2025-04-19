package com.vcfriend.backend.controller;

import com.vcfriend.backend.model.Cohort;
import com.vcfriend.backend.repository.CohortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cohorts")
@CrossOrigin(origins = "*")
public class CohortController {

    @Autowired
    private CohortRepository cohortRepository;

    // Get all cohorts
    @GetMapping
    public List<Cohort> getAll() {
        return cohortRepository.findAll();
    }

    // Get a specific cohort by ID
    @GetMapping("/{id}")
    public ResponseEntity<Cohort> getById(@PathVariable String id) {
        return cohortRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
