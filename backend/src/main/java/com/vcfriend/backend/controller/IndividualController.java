package com.vcfriend.backend.controller;

import com.vcfriend.backend.dto.IndividualDTO;
import com.vcfriend.backend.model.Individual;
import com.vcfriend.backend.service.IndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/individuals")
@CrossOrigin(origins = "http://localhost:3000")
public class IndividualController {

    @Autowired
    private IndividualService individualService;

    @GetMapping("/pedigrees/{pedigreeId}")
    public List<IndividualDTO> getIndividualsByPedigree(@PathVariable String pedigreeId) {
        return individualService.getByPedigreeId(pedigreeId);
    }

    @GetMapping("/{id}")
    public IndividualDTO getIndividualById(@PathVariable Long id) {
        Individual ind = individualService.getById(id);
        IndividualDTO dto = new IndividualDTO();
        dto.setId(ind.getId());
        dto.setName(ind.getName());
        dto.setClinicalDiagnosis(ind.getClinicalDiagnosis());
        dto.setDateOfBirth(ind.getDateOfBirth().toString());
        dto.setProband(ind.getProband());
        return dto;
    }

    @PostMapping("/relink/{individualId}/{pedigreeId}")
    public void relinkIndividualToPedigree(@PathVariable Long individualId, @PathVariable String pedigreeId) {
        individualService.relinkIndividual(individualId, pedigreeId);
    }

    @GetMapping("/debug")
    public List<Individual> debugAllIndividuals() {
        return individualService.getAllIndividuals();
    }

    @GetMapping("/testrepo/{pedigreeId}")
    public List<Individual> testRepo(@PathVariable String pedigreeId) {
        return individualService.testRepoFindByPedigree(pedigreeId);
    }
}