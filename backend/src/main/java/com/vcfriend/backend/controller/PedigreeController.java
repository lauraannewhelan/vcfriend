package com.vcfriend.backend.controller;

import com.vcfriend.backend.dto.PedigreeDTO;
import com.vcfriend.backend.model.Individual;
import com.vcfriend.backend.model.Pedigree;
import com.vcfriend.backend.repository.IndividualRepository;
import com.vcfriend.backend.repository.PedigreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedigrees")
@CrossOrigin(origins = "http://localhost:3000") // optional: CORS support for dev
public class PedigreeController {

    @Autowired
    private PedigreeRepository pedigreeRepository;

    @Autowired
    private IndividualRepository individualRepository;

    @GetMapping("/search")
    public ResponseEntity<PedigreeDTO> getPedigreeByQuery(@RequestParam String query) {
        Pedigree pedigree = pedigreeRepository.findByPedigreeId(query);

        if (pedigree == null) {
            return ResponseEntity.notFound().build();
        }

        List<Individual> individuals = individualRepository.findByPedigree_PedigreeId(query);
        List<String> studyIds = individuals.stream()
                .map(Individual::getStudyId)
                .collect(Collectors.toList());

        PedigreeDTO dto = new PedigreeDTO();
        dto.setPedigreeId(pedigree.getPedigreeId());
        dto.setDisease(pedigree.getDisease());
        dto.setGeneticDiagnosis(pedigree.getGeneticDiagnosis());
        dto.setNumSubjects(studyIds.size());
        dto.setMembers(studyIds);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Pedigree> createPedigree(@RequestBody Pedigree pedigree) {
        System.out.println("📥 Incoming pedigree: " + pedigree.getPedigreeId());

        if (pedigreeRepository.existsById(pedigree.getPedigreeId())) {
            return ResponseEntity.status(409).build(); // Conflict
        }

        Pedigree saved = pedigreeRepository.save(pedigree);
        return ResponseEntity.status(201).body(saved);
    }
}
