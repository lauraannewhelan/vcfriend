package com.vcfriend.backend.dto;

public class IndividualDTO {
    private Long id;
    private String name;
    private String clinicalDiagnosis;
    private String dateOfBirth;
    private Boolean proband;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClinicalDiagnosis() {
        return clinicalDiagnosis;
    }

    public void setClinicalDiagnosis(String clinicalDiagnosis) {
        this.clinicalDiagnosis = clinicalDiagnosis;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getProband() {
        return proband;
    }

    public void setProband(Boolean proband) {
        this.proband = proband;
    }
}
