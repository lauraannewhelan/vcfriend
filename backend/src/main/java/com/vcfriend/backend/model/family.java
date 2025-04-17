package com.vcfriend.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "family")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "family_name", nullable = false)
    private String familyName;

    // Constructors
    public Family() {}

    public Family(String familyName) {
        this.familyName = familyName;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
