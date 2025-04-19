package com.vcfriend.backend.controller;

import com.vcfriend.backend.model.Analysis;
import com.vcfriend.backend.repository.AnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analyses")
@CrossOrigin(origins = "*")
public class AnalysisController {

    @Autowired
    private AnalysisRepository analysisRepository;

    @GetMapping
    public List<Analysis> getAll() {
        return analysisRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Analysis> getById(@PathVariable String id) {
        return analysisRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
