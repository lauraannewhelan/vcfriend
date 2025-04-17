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

@RestController
@RequestMapping("/individuals")
public class IndividualController {

    @Autowired
    private IndividualRepository individualRepository;

    @Autowired
    private FamilyRepository familyRepository;

    // Get all individuals as DTOs (including familyId)
    @GetMapping
    public List<IndividualDTO> getAllIndividuals() {
        List<Individual> individuals = individualRepository.findAll();
        return individuals.stream().map(individual -> new IndividualDTO(
                individual.getId(),
                individual.getName(),
                individual.getDateOfBirth(),
                individual.getSex(),
                individual.getClinicalDiagnosis(),
                individual.getFamily() != null ? individual.getFamily().getId() : null
        )).collect(Collectors.toList());
    }

    // Create a new individual with associated family
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
        return ResponseEntity.ok(new IndividualDTO(
                saved.getId(),
                saved.getName(),
                saved.getDateOfBirth(),
                saved.getSex(),
                saved.getClinicalDiagnosis(),
                saved.getFamily().getId()
        ));
    }

    // DTO for incoming individual creation request
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
