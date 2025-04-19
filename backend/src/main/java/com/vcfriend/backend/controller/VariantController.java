package com.vcfriend.backend.controller;

import com.vcfriend.backend.model.GenomicVariant;
import com.vcfriend.backend.repository.GenomicVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variants")
@CrossOrigin(origins = "*")
public class VariantController {

    @Autowired
    private GenomicVariantRepository variantRepository;

    @GetMapping
    public List<GenomicVariant> getAll() {
        return variantRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenomicVariant> getById(@PathVariable Long id) {
        return variantRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
