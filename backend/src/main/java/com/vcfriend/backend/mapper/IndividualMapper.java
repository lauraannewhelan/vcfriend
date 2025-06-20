package com.vcfriend.backend.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vcfriend.backend.dto.IndividualDTO;
import com.vcfriend.backend.dto.PedigreeDTO;
import com.vcfriend.backend.model.Individual;
import com.vcfriend.backend.model.Pedigree;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class IndividualMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public IndividualDTO toDTO(Individual individual) {
        if (individual == null) return null;

        IndividualDTO dto = new IndividualDTO();
        dto.setId(individual.getId());
        dto.setName(individual.getName());
        dto.setClinicalDiagnosis(individual.getClinicalDiagnosis());
        dto.setDateOfBirth(individual.getDateOfBirth() != null ? individual.getDateOfBirth().toString() : "");
        dto.setProband(individual.getProband());
        dto.setSexLabel(individual.getSexLabel());  // Added sexLabel mapping
        return dto;
    }

    public Individual fromDTO(IndividualDTO dto) {
        if (dto == null) return null;

        Individual individual = new Individual();
        individual.setId(dto.getId());
        individual.setName(dto.getName());
        individual.setClinicalDiagnosis(dto.getClinicalDiagnosis());
        if (dto.getDateOfBirth() != null && !dto.getDateOfBirth().isEmpty()) {
            individual.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
        }
        individual.setProband(dto.getProband());
        individual.setSexLabel(dto.getSexLabel());  // Added sexLabel mapping
        return individual;
    }

    public List<IndividualDTO> toDTOList(List<Individual> individuals) {
        if (individuals == null) return Collections.emptyList();
        return individuals.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<PedigreeDTO> toPedigreeDTOs(List<Pedigree> pedigrees) {
        if (pedigrees == null) return Collections.emptyList();

        return pedigrees.stream().map(p -> {
            PedigreeDTO dto = new PedigreeDTO();
            dto.setPedigreeId(p.getPedigreeId());
            dto.setDisease(p.getDisease());
            dto.setNumSubjects(p.getNumSubjects());

            try {
                dto.setMembers(objectMapper.readValue(p.getMembers(), new TypeReference<List<String>>() {}));
            } catch (Exception e) {
                dto.setMembers(List.of()); // fallback on failure
            }

            return dto;
        }).collect(Collectors.toList());
    }
}
