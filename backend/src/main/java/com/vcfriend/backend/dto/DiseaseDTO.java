package com.vcfriend.backend.dto;

public class DiseaseDTO {
    private BeaconTermDTO diseaseCode;
    private String ageOfOnset;
    private String severity;
    private Boolean familyHistory;
    private String stage;
    private String notes;

    public BeaconTermDTO getDiseaseCode() {
        return diseaseCode;
    }

    public void setDiseaseCode(BeaconTermDTO diseaseCode) {
        this.diseaseCode = diseaseCode;
    }

    public String getAgeOfOnset() {
        return ageOfOnset;
    }

    public void setAgeOfOnset(String ageOfOnset) {
        this.ageOfOnset = ageOfOnset;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Boolean getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(Boolean familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
