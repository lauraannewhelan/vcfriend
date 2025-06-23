package com.vcfriend.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Individual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clinicalDiagnosis;
    private LocalDate dateOfBirth;
    private Boolean proband;

    @Column(name = "sex_label")
    private String sexLabel;

    @Column(name = "study_id", unique = true)
    private String studyId;

    @ManyToOne
    @JoinColumn(name = "pedigree_id")
    @JsonBackReference
    private Pedigree pedigree;

    // Getters
    public Long getId() { return id; }
    public String getClinicalDiagnosis() { return clinicalDiagnosis; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public Boolean getProband() { return proband; }
    public String getSexLabel() { return sexLabel; }
    public String getStudyId() { return studyId; }
    public Pedigree getPedigree() { return pedigree; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setClinicalDiagnosis(String clinicalDiagnosis) { this.clinicalDiagnosis = clinicalDiagnosis; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setProband(Boolean proband) { this.proband = proband; }
    public void setSexLabel(String sexLabel) { this.sexLabel = sexLabel; }
    public void setStudyId(String studyId) { this.studyId = studyId; }
    public void setPedigree(Pedigree pedigree) { this.pedigree = pedigree; }
}
