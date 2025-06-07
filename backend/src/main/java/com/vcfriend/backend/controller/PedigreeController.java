package com.vcfriend.backend.controller;

import com.vcfriend.backend.model.Pedigree;
import com.vcfriend.backend.repository.PedigreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedigrees")
public class PedigreeController {

    @Autowired
    private PedigreeRepository pedigreeRepository;

    @GetMapping("/search")
    public ResponseEntity<Pedigree> getPedigreeByQuery(@RequestParam String query) {
        System.out.println("Received query parameter: " + query);

        Pedigree pedigree = pedigreeRepository.findByPedigreeId(query);

        if (pedigree != null) {
            return ResponseEntity.ok(pedigree);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
