package com.vcfriend.backend.mapper;

import com.vcfriend.backend.dto.IndividualDTO;
import com.vcfriend.backend.model.Individual;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IndividualMapper {

    public IndividualDTO toDTO(Individual individual) {
        IndividualDTO dto = new IndividualDTO();
        dto.setId(individual.getId());
        dto.setClinicalDiagnosis(individual.getClinicalDiagnosis());
        dto.setDateOfBirth(individual.getDateOfBirth() != null ? individual.getDateOfBirth().toString() : null);
        dto.setProband(individual.getProband());
        dto.setSexLabel(individual.getSexLabel());
        dto.setStudyId(individual.getStudyId());
        dto.setPedigreeId(individual.getPedigree().getPedigreeId());
        return dto;
    }

    public List<IndividualDTO> toDTOList(List<Individual> individuals) {
        return individuals.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
