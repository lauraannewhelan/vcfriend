package com.vcfriend.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("family-individuals")
    private List<Individual> individuals;

    public FamilyEntity() {}

    public FamilyEntity(String familyName) {
        this.familyName = familyName;
    }

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
