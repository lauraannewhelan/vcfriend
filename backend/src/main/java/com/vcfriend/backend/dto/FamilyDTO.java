package com.vcfriend.backend.dto;

import java.util.List;

public class FamilyDTO {

    private Long id;
    private String familyName;
    private String probandName;
    private List<IndividualDTO> individuals;

    public FamilyDTO() {}

    public FamilyDTO(Long id, String familyName, String probandName, List<IndividualDTO> individuals) {
        this.id = id;
        this.familyName = familyName;
        this.probandName = probandName;
        this.individuals = individuals;
    }

    public Long getId() {
        return id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getProbandName() {
        return probandName;
    }

    public List<IndividualDTO> getIndividuals() {
        return individuals;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setProbandName(String probandName) {
        this.probandName = probandName;
    }

    public void setIndividuals(List<IndividualDTO> individuals) {
        this.individuals = individuals;
    }
}
