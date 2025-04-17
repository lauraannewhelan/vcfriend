package com.vcfriend.backend.dto;

import java.time.LocalDate;

public class IndividualDTO {

    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String sex;
    private String clinicalDiagnosis;
    private Long familyId;

    public IndividualDTO() {}

    public IndividualDTO(Long id, String name, LocalDate dateOfBirth, String sex, String clinicalDiagnosis, Long familyId) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.clinicalDiagnosis = clinicalDiagnosis;
        this.familyId = familyId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public String getClinicalDiagnosis() {
        return clinicalDiagnosis;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setClinicalDiagnosis(String clinicalDiagnosis) {
        this.clinicalDiagnosis = clinicalDiagnosis;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }
}
