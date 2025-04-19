package com.vcfriend.backend.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vcfriend.backend.dto.PedigreeDTO;
import com.vcfriend.backend.model.Pedigree;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IndividualMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<PedigreeDTO> toPedigreeDTOs(List<Pedigree> pedigrees) {
        if (pedigrees == null) return null;
        return pedigrees.stream().map(p -> {
            PedigreeDTO dto = new PedigreeDTO();
            dto.setPedigreeId(p.getPedigreeId());
            dto.setDisease(p.getDisease());
            dto.setNumSubjects(p.getNumSubjects());

            try {
                dto.setMembers(objectMapper.readValue(p.getMembers(), new TypeReference<List<String>>() {}));
            } catch (Exception e) {
                dto.setMembers(List.of()); // fallback if parsing fails
            }

            return dto;
        }).collect(Collectors.toList());
    }
}
