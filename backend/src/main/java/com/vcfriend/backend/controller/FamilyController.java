package com.vcfriend.backend.controller;

import com.vcfriend.backend.model.FamilyEntity;
import com.vcfriend.backend.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/families")
public class FamilyController {

    @Autowired
    private FamilyRepository familyRepository;

    // GET /families
    @GetMapping
    public List<FamilyEntity> getAllFamilies() {
        return familyRepository.findAll();
    }

    // POST /families
    @PostMapping
    public FamilyEntity createFamily(@RequestBody FamilyEntity family) {
        return familyRepository.save(family);
    }
}
