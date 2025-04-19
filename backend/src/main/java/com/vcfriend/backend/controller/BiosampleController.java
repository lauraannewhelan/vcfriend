package com.vcfriend.backend.controller;

import com.vcfriend.backend.model.Biosample;
import com.vcfriend.backend.repository.BiosampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/biosamples")
@CrossOrigin(origins = "*")
public class BiosampleController {

    @Autowired
    private BiosampleRepository biosampleRepository;

    @GetMapping
    public List<Biosample> getAll() {
        return biosampleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Biosample> getById(@PathVariable String id) {
        return biosampleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
