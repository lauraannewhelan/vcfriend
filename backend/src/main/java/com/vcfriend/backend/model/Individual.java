package com.vcfriend.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Individual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String clinicalDiagnosis;
    private LocalDate dateOfBirth;

    private Boolean proband;  // ✅ Add this field

    @ManyToOne
    @JoinColumn(name = "pedigree_id")
    @JsonBackReference
    private Pedigree pedigree;

    // ✅ Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClinicalDiagnosis() {
        return clinicalDiagnosis;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Boolean getProband() {
        return proband;
    }

    public Pedigree getPedigree() {
        return pedigree;
    }

    // ✅ Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClinicalDiagnosis(String clinicalDiagnosis) {
        this.clinicalDiagnosis = clinicalDiagnosis;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setProband(Boolean proband) {
        this.proband = proband;
    }

    public void setPedigree(Pedigree pedigree) {
        this.pedigree = pedigree;
    }
}
