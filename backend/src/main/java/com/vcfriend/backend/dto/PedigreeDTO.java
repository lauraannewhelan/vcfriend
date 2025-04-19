package com.vcfriend.backend.dto;

import java.util.List;

public class PedigreeDTO {
    private String pedigreeId;
    private String disease;
    private Integer numSubjects;
    private List<String> members;

    public String getPedigreeId() {
        return pedigreeId;
    }

    public void setPedigreeId(String pedigreeId) {
        this.pedigreeId = pedigreeId;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Integer getNumSubjects() {
        return numSubjects;
    }

    public void setNumSubjects(Integer numSubjects) {
        this.numSubjects = numSubjects;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
