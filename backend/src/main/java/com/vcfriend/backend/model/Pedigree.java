package com.vcfriend.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "individual_pedigree")  // Ensure this matches your actual table name
public class Pedigree {

    @Id
    @Column(name = "pedigree_id")  // Matches the column name in the table
    private String pedigreeId;

    private String disease;

    @Column(name = "num_subjects")
    private Integer numSubjects;

    private String members;

    @Column(name = "genetic_diagnosis")
    @JsonProperty("genetic_diagnosis")  // Maps JSON key to snake_case
    private String geneticDiagnosis;

    // One-to-many relationship with Individual
    @OneToMany(mappedBy = "pedigree", fetch = FetchType.LAZY)  // Many Individuals belong to one Pedigree
    @JsonManagedReference  // Prevent infinite recursion during JSON serialization
    private List<Individual> individuals;

    // Getters and setters

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

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(List<Individual> individuals) {
        this.individuals = individuals;
    }
}
