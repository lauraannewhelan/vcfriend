package com.vcfriend.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sample")
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sampleLabel;
    private String tissueType;

    // ✅ Back reference to Individual (matches Individual.java)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "individual_id")
    @JsonBackReference(value = "individual-samples")
    private Individual individual;

    // ✅ Forward reference to VcfFile (matches VcfFile.java)
    @OneToMany(mappedBy = "sample", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "sample-vcffiles")
    private List<VcfFile> vcfFiles;

    public Long getId() {
        return id;
    }

    public String getSampleLabel() {
        return sampleLabel;
    }

    public void setSampleLabel(String sampleLabel) {
        this.sampleLabel = sampleLabel;
    }

    public String getTissueType() {
        return tissueType;
    }

    public void setTissueType(String tissueType) {
        this.tissueType = tissueType;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public List<VcfFile> getVcfFiles() {
        return vcfFiles;
    }

    public void setVcfFiles(List<VcfFile> vcfFiles) {
        this.vcfFiles = vcfFiles;
    }
}
