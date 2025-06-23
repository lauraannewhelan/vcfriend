package com.vcfriend.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "individual_pedigree")
public class Pedigree {

    @Id
    @Column(name = "pedigree_id")
    @JsonProperty("pedigree_id")
    private String pedigreeId;

    @JsonProperty("disease")
    private String disease;

    @Column(name = "num_subjects")
    @JsonProperty("num_subjects")
    private Integer numSubjects;

    @JsonProperty("members")
    private String members;

    @Column(name = "genetic_diagnosis")
    @JsonProperty("genetic_diagnosis")
    private String geneticDiagnosis;

    @Column(name = "clinical_diagnosis")
    @JsonProperty("clinical_diagnosis")
    private String clinicalDiagnosis;

    @OneToMany(mappedBy = "pedigree", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Individual> individuals;

    // Getters and Setters
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

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getGeneticDiagnosis() {
        return geneticDiagnosis;
    }

    public void setGeneticDiagnosis(String geneticDiagnosis) {
        this.geneticDiagnosis = geneticDiagnosis;
    }

    public String getClinicalDiagnosis() {
        return clinicalDiagnosis;
    }

    public void setClinicalDiagnosis(String clinicalDiagnosis) {
        this.clinicalDiagnosis = clinicalDiagnosis;
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(List<Individual> individuals) {
        this.individuals = individuals;
    }
}
