package com.vcfriend.backend.dto;

public class IndividualDTO {
    private Long id;
    private String clinicalDiagnosis;
    private String dateOfBirth;
    private Boolean proband;
    private String sexLabel;
    private String studyId;
    private String pedigreeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSexLabel() {
        return sexLabel;
    }

    public void setSexLabel(String sexLabel) {
        this.sexLabel = sexLabel;
    }

    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId;
    }

    public String getPedigreeId() {
        return pedigreeId;
    }

    public void setPedigreeId(String pedigreeId) {
        this.pedigreeId = pedigreeId;
    }
}
