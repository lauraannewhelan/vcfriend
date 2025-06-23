package com.vcfriend.backend.service;

import com.vcfriend.backend.dto.IndividualDTO;
import com.vcfriend.backend.mapper.IndividualMapper;
import com.vcfriend.backend.model.Individual;
import com.vcfriend.backend.model.Pedigree;
import com.vcfriend.backend.repository.IndividualRepository;
import com.vcfriend.backend.repository.PedigreeRepository;
import htsjdk.variant.vcf.VCFFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndividualService {

    @Autowired
    private IndividualRepository individualRepository;

    @Autowired
    private PedigreeRepository pedigreeRepository;

    @Autowired
    private IndividualMapper individualMapper;

    public List<IndividualDTO> getByPedigreeId(String pedigreeId) {
        List<Individual> individuals = individualRepository.findByPedigree_PedigreeId(pedigreeId);
        System.out.println("📢 Found " + individuals.size() + " individuals for pedigree: " + pedigreeId);
        for (Individual ind : individuals) {
            System.out.println("🧬 " + ind.getStudyId());
        }
        return individualMapper.toDTOList(individuals);
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
    }

    public List<Individual> getAllIndividuals() {
        return individualRepository.findAll();
    }

    public List<Individual> testRepoFindByPedigree(String pedigreeId) {
        return individualRepository.findByPedigree_PedigreeId(pedigreeId);
    }

    public void save(Individual individual) {
        individualRepository.save(individual);
    }

    public Individual createFromDTO(IndividualDTO dto) {
        // Check for duplicate studyId
        individualRepository.findByStudyId(dto.getStudyId()).ifPresent(existing -> {
            throw new IllegalArgumentException("Individual with Study ID '" + dto.getStudyId() + "' already exists.");
        });

        Pedigree pedigree = pedigreeRepository.findById(dto.getPedigreeId())
                .orElseThrow(() -> new RuntimeException("Pedigree not found: " + dto.getPedigreeId()));

        Individual individual = new Individual();
        individual.setStudyId(dto.getStudyId());
        individual.setClinicalDiagnosis(dto.getClinicalDiagnosis());
        if (dto.getDateOfBirth() != null && !dto.getDateOfBirth().isBlank()) {
            individual.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
        }
        individual.setProband(dto.getProband());
        individual.setSexLabel(dto.getSexLabel());
        individual.setPedigree(pedigree);

        return individualRepository.save(individual);
    }

    public List<String> getVariantsFromVCF(Long individualId) {
        String rootPath = System.getProperty("user.dir");
        File vcf = new File(rootPath + "/vcf_storage/" + individualId + ".vcf");

        if (!vcf.exists()) {
            return List.of("No VCF file found.");
        }

        try (VCFFileReader reader = new VCFFileReader(vcf, false)) {
            return reader.iterator()
                    .stream()
                    .map(variant -> variant.getContig() + "\t" + variant.getStart() + "\t" +
                            variant.getReference().getBaseString() + " → " +
                            variant.getAlternateAlleles().stream().map(Object::toString).collect(Collectors.joining(",")))
                    .collect(Collectors.toList());
        }
    }
}
