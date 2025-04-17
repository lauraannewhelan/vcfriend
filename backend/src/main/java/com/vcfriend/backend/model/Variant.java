package com.vcfriend.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "variant")
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chrom")
    private String chrom;

    @Column(name = "pos")
    private Integer pos;

    @Column(name = "variant_id")
    private String variantId;

    @Column(name = "ref")
    private String ref;

    @Column(name = "alt")
    private String alt;

    // Relationship to Individual
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "individual_id")
    private Individual individual;

    // === Constructors ===
    public Variant() {}

    public Variant(String chrom, Integer pos, String variantId, String ref, String alt, Individual individual) {
        this.chrom = chrom;
        this.pos = pos;
        this.variantId = variantId;
        this.ref = ref;
        this.alt = alt;
        this.individual = individual;
    }

    // === Getters and Setters ===
    public Long getId() {
        return id;
    }

    public String getChrom() {
        return chrom;
    }

    public void setChrom(String chrom) {
        this.chrom = chrom;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public String getVariantId() {
        return variantId;
    }

    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }
}
