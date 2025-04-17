//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.vcfriend.backend.controller;

import com.vcfriend.backend.dto.IndividualDTO;
import com.vcfriend.backend.model.FamilyEntity;
import com.vcfriend.backend.model.Individual;
import com.vcfriend.backend.repository.FamilyRepository;
import com.vcfriend.backend.repository.IndividualRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/individuals"})
public class IndividualController {
    @Autowired
    private IndividualRepository individualRepository;
    @Autowired
    private FamilyRepository familyRepository;

    @GetMapping
    public List<IndividualDTO> getAllIndividuals() {
        return (List)this.individualRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> createIndividual(@RequestBody com.vcfriend.backend.controller.IndividualController.IndividualRequest request) {
        Optional<FamilyEntity> familyOpt = this.familyRepository.findById(request.getFamilyId());
        if (familyOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Family with ID " + request.getFamilyId() + " not found.");
        } else {
            FamilyEntity family = (FamilyEntity)familyOpt.get();
            Individual individual = new Individual();
            individual.setName(request.getName());
            individual.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));
            individual.setSex(request.getSex());
            individual.setClinicalDiagnosis(request.getClinicalDiagnosis());
            individual.setFamily(family);
            Individual saved = (Individual)this.individualRepository.save(individual);
            return ResponseEntity.ok(this.toDTO(saved));
        }
    }

    @GetMapping({"/family/{familyId}"})
    public ResponseEntity<?> getIndividualsByFamilyId(@PathVariable Long familyId) {
        Optional<FamilyEntity> familyOpt = this.familyRepository.findById(familyId);
        if (familyOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Family not found with ID " + familyId);
        } else {
            List<IndividualDTO> individuals = (List)this.individualRepository.findAll().stream().filter((i) -> i.getFamily() != null && i.getFamily().getId().equals(familyId)).map(this::toDTO).collect(Collectors.toList());
            return ResponseEntity.ok(individuals);
        }
    }

    private IndividualDTO toDTO(Individual individual) {
        return new IndividualDTO(individual.getId(), individual.getName(), individual.getDateOfBirth(), individual.getSex(), individual.getClinicalDiagnosis(), individual.getFamily() != null ? individual.getFamily().getId() : null);
    }

    public static class IndividualRequest {
        private String name;
        private String dateOfBirth;
        private String sex;
        private String clinicalDiagnosis;
        private Long familyId;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDateOfBirth() {
            return this.dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getSex() {
            return this.sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getClinicalDiagnosis() {
            return this.clinicalDiagnosis;
        }

        public void setClinicalDiagnosis(String clinicalDiagnosis) {
            this.clinicalDiagnosis = clinicalDiagnosis;
        }

        public Long getFamilyId() {
            return this.familyId;
        }

        public void setFamilyId(Long familyId) {
            this.familyId = familyId;
        }
    }
}
