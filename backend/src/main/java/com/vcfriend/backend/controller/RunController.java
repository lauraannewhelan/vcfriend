package com.vcfriend.backend.controller;

import com.vcfriend.backend.model.Run;
import com.vcfriend.backend.repository.RunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/runs")
@CrossOrigin(origins = "*")
public class RunController {

    private final RunRepository runRepository;

    @Autowired
    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping
    public List<Run> getAllRuns() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Run> getRunById(@PathVariable String id) {
        return runRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
