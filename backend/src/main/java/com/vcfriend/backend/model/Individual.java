package com.vcfriend.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "individual")
public class Individual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationship to Family (hidden in JSON)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    @JsonIgnore
    private FamilyEntity family;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "sex")
    private String sex;

    @Column(name = "clinical_diagnosis")
    private String clinicalDiagnosis;

    // ✅ List of genetic variants for this individual
    @OneToMany(mappedBy = "individual", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Variant> variants;

    // === Constructors ===
    public Individual() {}

    public Individual(FamilyEntity family, String name, LocalDate dateOfBirth, String sex, String clinicalDiagnosis) {
        this.family = family;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.clinicalDiagnosis = clinicalDiagnosis;
    }

    // === Getters & Setters ===
    public Long getId() {
        return id;
    }

    public FamilyEntity getFamily() {
        return family;
    }

    public void setFamily(FamilyEntity family) {
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClinicalDiagnosis() {
        return clinicalDiagnosis;
    }

    public void setClinicalDiagnosis(String clinicalDiagnosis) {
        this.clinicalDiagnosis = clinicalDiagnosis;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }
}
