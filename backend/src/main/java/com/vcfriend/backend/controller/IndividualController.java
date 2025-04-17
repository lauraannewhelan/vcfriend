package com.vcfriend.backend.controller;

import com.vcfriend.backend.model.FamilyEntity;
import com.vcfriend.backend.model.Individual;
import com.vcfriend.backend.repository.FamilyRepository;
import com.vcfriend.backend.repository.IndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// パート1：Individualエンドポイントのコントローラー
@RestController
@RequestMapping("/individuals")
public class IndividualController {

    @Autowired
    private IndividualRepository individualRepository;

    @Autowired
    private FamilyRepository familyRepository;

    // パート2：全個人を取得する
    @GetMapping
    public List<Individual> getAllIndividuals() {
        return individualRepository.findAll();
    }

    // パート3：個人を新規作成する
    @PostMapping
    public ResponseEntity<?> createIndividual(@RequestBody IndividualRequest request) {
        Optional<FamilyEntity> familyOpt = familyRepository.findById(request.getFamilyId());

        if (familyOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Family with ID " + request.getFamilyId() + " not found.");
        }

        FamilyEntity family = familyOpt.get();

        Individual individual = new Individual();
        individual.setName(request.getName());
        individual.setDateOfBirth(request.getDateOfBirth());
        individual.setSex(request.getSex());
        individual.setClinicalDiagnosis(request.getClinicalDiagnosis());
        individual.setFamily(family);

        Individual saved = individualRepository.save(individual);
        return ResponseEntity.ok(saved);
    }

    // パート4：リクエスト用DTO
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
