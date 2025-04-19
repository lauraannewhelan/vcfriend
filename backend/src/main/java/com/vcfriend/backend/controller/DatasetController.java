package com.vcfriend.backend.controller;

import com.vcfriend.backend.model.Dataset;
import com.vcfriend.backend.repository.DatasetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/datasets")
@CrossOrigin(origins = "*")
public class DatasetController {

    @Autowired
    private DatasetRepository datasetRepository;

    @GetMapping
    public List<Dataset> getAll() {
        return datasetRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dataset> getById(@PathVariable String id) {
        return datasetRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
