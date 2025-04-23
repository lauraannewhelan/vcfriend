package com.vcfriend.backend.service;

import com.vcfriend.backend.dto.IndividualDTO;
import com.vcfriend.backend.model.Individual;
import com.vcfriend.backend.model.Pedigree;
import com.vcfriend.backend.repository.IndividualRepository;
import com.vcfriend.backend.repository.PedigreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndividualService {

    @Autowired
    private IndividualRepository individualRepository;

    @Autowired
    private PedigreeRepository pedigreeRepository;

    public List<IndividualDTO> getByPedigreeId(String pedigreeId) {
        List<Individual> individuals = individualRepository.findByPedigree_PedigreeId(pedigreeId);

        System.out.println("🔍 JPA returned " + individuals.size() + " individuals for pedigreeId: " + pedigreeId);
        for (Individual i : individuals) {
            System.out.println("👤 " + i.getName() + " (ID " + i.getId() + ")");
        }

        return individuals.stream().map(ind -> {
            IndividualDTO dto = new IndividualDTO();
            dto.setId(ind.getId());
            dto.setName(ind.getName());
            dto.setClinicalDiagnosis(ind.getClinicalDiagnosis());
            dto.setDateOfBirth(ind.getDateOfBirth().toString());
            dto.setProband(ind.getProband());
            return dto;
        }).collect(Collectors.toList());
    }

    public Individual getById(Long id) {
        return individualRepository.findById(id).orElse(null);
    }

    public void relinkIndividual(Long individualId, String pedigreeId) {
        Individual individual = individualRepository.findById(individualId)
                .orElseThrow(() -> new RuntimeException("Individual not found"));

        Pedigree pedigree = pedigreeRepository.findById(pedigreeId)
                .orElseThrow(() -> new RuntimeException("Pedigree not found"));

        individual.setPedigree(pedigree);
        individualRepository.save(individual);

        System.out.println("✅ Relinked individual " + individual.getName() + " to pedigree " + pedigreeId);
    }

    public List<Individual> getAllIndividuals() {
        return individualRepository.findAll();
    }

    public List<Individual> testRepoFindByPedigree(String pedigreeId) {
        return individualRepository.findByPedigree_PedigreeId(pedigreeId);
    }
}