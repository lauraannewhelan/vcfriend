package com.vcfriend.backend.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class Disease {

    private String ageOfOnset;
    private String severity;
    private Boolean familyHistory;
    private String stage;
    private String notes;

    @Embedded
    private BeaconTerm diseaseCode;

    public String getAgeOfOnset() { return ageOfOnset; }
    public void setAgeOfOnset(String ageOfOnset) { this.ageOfOnset = ageOfOnset; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public Boolean getFamilyHistory() { return familyHistory; }
    public void setFamilyHistory(Boolean familyHistory) { this.familyHistory = familyHistory; }

    public String getStage() { return stage; }
    public void setStage(String stage) { this.stage = stage; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public BeaconTerm getDiseaseCode() { return diseaseCode; }
    public void setDiseaseCode(BeaconTerm diseaseCode) { this.diseaseCode = diseaseCode; }
}
