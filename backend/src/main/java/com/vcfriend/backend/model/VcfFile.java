package com.vcfriend.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vcf_file")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VcfFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String referenceGenome;
    private LocalDateTime uploadDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sample_id")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Sample sample;

    @OneToMany(mappedBy = "vcfFile", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private List<Variant> variants;

    public Long getId() { return id; }

    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }

    public String getReferenceGenome() { return referenceGenome; }
    public void setReferenceGenome(String referenceGenome) { this.referenceGenome = referenceGenome; }

    public LocalDateTime getUploadDate() { return uploadDate; }
    public void setUploadDate(LocalDateTime uploadDate) { this.uploadDate = uploadDate; }

    public Sample getSample() { return sample; }
    public void setSample(Sample sample) { this.sample = sample; }

    public List<Variant> getVariants() { return variants; }
    public void setVariants(List<Variant> variants) { this.variants = variants; }
}
