package com.vcfriend.backend.controller;

import com.vcfriend.backend.model.FamilyEntity;
import com.vcfriend.backend.model.Individual;
import com.vcfriend.backend.repository.FamilyRepository;
import com.vcfriend.backend.repository.IndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/families")
public class FamilyController {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private IndividualRepository individualRepository;

    // Get all families
    @GetMapping
    public List<FamilyEntity> getAllFamilies() {
        return familyRepository.findAll();
    }

    // Create a new family
    @PostMapping
    public FamilyEntity createFamily(@RequestBody FamilyEntity family) {
        return familyRepository.save(family);
    }

    // ✅ Get a specific family by ID and include its individuals
    @GetMapping("/{id}")
    public FamilyEntity getFamilyById(@PathVariable Long id) {
        FamilyEntity family = familyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Family not found with id: " + id));

        List<Individual> individuals = individualRepository.findByFamilyId(id);
        family.setIndividuals(individuals);

        return family;
    }
}
