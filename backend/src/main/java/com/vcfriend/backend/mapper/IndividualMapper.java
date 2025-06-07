package com.vcfriend.backend.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vcfriend.backend.dto.IndividualDTO;
import com.vcfriend.backend.dto.PedigreeDTO;
import com.vcfriend.backend.model.Individual;
import com.vcfriend.backend.model.Pedigree;
import org.springframework.stereotype.Component;

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

        return dto;
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
