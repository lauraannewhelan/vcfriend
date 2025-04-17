package com.vcfriend.backend.controller;

import com.vcfriend.backend.model.FamilyEntity;
import com.vcfriend.backend.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/families")
@CrossOrigin(origins = "*")
public class FamilyController {

    @Autowired
    private FamilyRepository familyRepository;

    @GetMapping
    public List<FamilyEntity> getAllFamilies() {
        return familyRepository.findAll();
    }

    @GetMapping("/{id}")
    public FamilyEntity getFamilyById(@PathVariable Long id) {
        return familyRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Family not found with id: " + id));
    }
}
