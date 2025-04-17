package com.vcfriend.backend.controller;

import com.vcfriend.backend.dto.IndividualDTO;
import com.vcfriend.backend.model.FamilyEntity;
import com.vcfriend.backend.model.Individual;
import com.vcfriend.backend.repository.FamilyRepository;
import com.vcfriend.backend.repository.IndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Part 1: Controller for Individual endpoints
@RestController
@RequestMapping("/individuals")
public class IndividualController {

    @Autowired
    private IndividualRepository individualRepository;

    @Autowired
    private FamilyRepository familyRepository;

    // Part 2: Get all individuals
    @GetMapping
    public List<IndividualDTO> getAllIndividuals() {
        return individualRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Part 3: Create new individual
    @PostMapping
    public ResponseEntity<?> createIndividual(@RequestBody IndividualRequest request) {
        Optional<FamilyEntity> familyOpt = familyRepository.findById(request.getFamilyId());

        if (familyOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Family with ID " + request.getFamilyId() + " not found.");
        }

        FamilyEntity family = familyOpt.get();

        Individual individual = new Individual();
        individual.setName(request.getName());
        individual.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));
        individual.setSex(request.getSex());
        individual.setClinicalDiagnosis(request.getClinicalDiagnosis());
        individual.setFamily(family);

        Individual saved = individualRepository.save(individual);
        return ResponseEntity.ok(toDTO(saved));
    }

    // ✅ Part 4: NEW – Get individuals by family ID
    @GetMapping("/family/{familyId}")
    public ResponseEntity<?> getIndividualsByFamilyId(@PathVariable Long familyId) {
        Optional<FamilyEntity> familyOpt = familyRepository.findById(familyId);

        if (familyOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Family not found with ID " + familyId);
        }

        List<IndividualDTO> individuals = individualRepository.findAll().stream()
                .filter(i -> i.getFamily() != null && i.getFamily().getId().equals(familyId))
                .map(this::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(individuals);
    }

    // Part 5: Map entity to DTO
    private IndividualDTO toDTO(Individual individual) {
        return new IndividualDTO(
                individual.getId(),
                individual.getName(),
                individual.getDateOfBirth(),
                individual.getSex(),
                individual.getClinicalDiagnosis(),
                individual.getFamily() != null ? individual.getFamily().getId() : null
        );
    }

    // Part 6: Request DTO
    public static class IndividualRequest {
        private String name;
        private String dateOfBirth;
        private String sex;
        private String clinicalDiagnosis;
        private Long familyId;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getDateOfBirth() { return dateOfBirth; }
        public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

        public String getSex() { return sex; }
        public void setSex(String sex) { this.sex = sex; }

        public String getClinicalDiagnosis() { return clinicalDiagnosis; }
        public void setClinicalDiagnosis(String clinicalDiagnosis) { this.clinicalDiagnosis = clinicalDiagnosis; }

        public Long getFamilyId() { return familyId; }
        public void setFamilyId(Long familyId) { this.familyId = familyId; }
    }
}
