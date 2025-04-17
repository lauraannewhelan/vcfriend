package com.vcfriend.backend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "family")
public class FamilyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "family_name", nullable = false)
    private String familyName;

    // Not persisted in DB, but included in response
    @Transient
    private List<Individual> individuals;

    // === Constructors ===
    public FamilyEntity() {}

    public FamilyEntity(String familyName) {
        this.familyName = familyName;
    }

    // === Getters and setters ===
    public Long getId() {
        return id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(List<Individual> individuals) {
        this.individuals = individuals;
    }
}
