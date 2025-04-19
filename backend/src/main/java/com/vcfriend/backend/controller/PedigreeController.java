package com.vcfriend.backend.controller;

import com.vcfriend.backend.model.Pedigree;
import com.vcfriend.backend.repository.PedigreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")  // Allow React app to communicate with the backend
@RestController
@RequestMapping("/api/pedigrees")
public class PedigreeController {

    @Autowired
    private PedigreeRepository pedigreeRepository;

    // Endpoint for searching Pedigree by query parameter
    @GetMapping("/search")
    public ResponseEntity<Pedigree> getPedigreeByQuery(@RequestParam String query) {
        System.out.println("Received query parameter: " + query);

        // Query the Pedigree by pedigree_id
        Pedigree pedigree = pedigreeRepository.findByPedigreeId(query);

        // If Pedigree is found, return it, otherwise return 404
        if (pedigree != null) {
            return ResponseEntity.ok(pedigree);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
