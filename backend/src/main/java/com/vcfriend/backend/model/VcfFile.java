package com.vcfriend.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "vcf_file")
public class VcfFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String referenceGenome;
    private String uploadDate;

    // ✅ Back reference to Sample (matches Sample.java)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sample_id")
    @JsonBackReference(value = "sample-vcffiles")
    private Sample sample;

    // Getters and setters...
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFilename() { return filename; }

    public void setFilename(String filename) { this.filename = filename; }

    public String getReferenceGenome() { return referenceGenome; }

    public void setReferenceGenome(String referenceGenome) { this.referenceGenome = referenceGenome; }

    public String getUploadDate() { return uploadDate; }

    public void setUploadDate(String uploadDate) { this.uploadDate = uploadDate; }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }
}
