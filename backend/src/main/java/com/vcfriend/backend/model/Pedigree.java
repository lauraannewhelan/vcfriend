package com.vcfriend.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "individual_pedigree")  // Ensure this matches the table name
public class Pedigree {

    @Id
    @Column(name = "pedigree_id")  // Ensure this matches the column name in the table
    private String pedigreeId;

    private String disease;

    @Column(name = "num_subjects")
    private Integer numSubjects;

    private String members;

    // One-to-many relationship with Individual
    @OneToMany(mappedBy = "pedigree", fetch = FetchType.LAZY)  // Many Individuals belong to one Pedigree
    @JsonManagedReference  // Add this to prevent infinite recursion
    private List<Individual> individuals;

    // Getter for pedigreeId
    public String getPedigreeId() {
        return pedigreeId;
    }

    // Setter for pedigreeId (if needed)
    public void setPedigreeId(String pedigreeId) {
        this.pedigreeId = pedigreeId;
    }

    // Getters and setters for other fields (disease, numSubjects, members, individuals)
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

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(List<Individual> individuals) {
        this.individuals = individuals;
    }
}
