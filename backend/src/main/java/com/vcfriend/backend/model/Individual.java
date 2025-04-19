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

    // Many-to-one relationship with Pedigree (many individuals belong to one pedigree)
    @ManyToOne
    @JoinColumn(name = "pedigree_id")  // This column will be the foreign key to the Pedigree table
    @JsonBackReference  // Add this to stop infinite recursion on the Individual side
    private Pedigree pedigree;

    // Getters and setters...
}
