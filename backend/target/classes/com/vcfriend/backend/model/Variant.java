//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.vcfriend.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "variant"
)
public class Variant {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            name = "chrom"
    )
    private String chrom;
    @Column(
            name = "pos"
    )
    private Integer pos;
    @Column(
            name = "variant_id"
    )
    private String variantId;
    @Column(
            name = "ref"
    )
    private String ref;
    @Column(
            name = "alt"
    )
    private String alt;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "individual_id"
    )
    private Individual individual;

    public Variant() {
    }

    public Variant(String chrom, Integer pos, String variantId, String ref, String alt, Individual individual) {
        this.chrom = chrom;
        this.pos = pos;
        this.variantId = variantId;
        this.ref = ref;
        this.alt = alt;
        this.individual = individual;
    }

    public Long getId() {
        return this.id;
    }

    public String getChrom() {
        return this.chrom;
    }

    public void setChrom(String chrom) {
        this.chrom = chrom;
    }

    public Integer getPos() {
        return this.pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public String getVariantId() {
        return this.variantId;
    }

    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }

    public String getRef() {
        return this.ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAlt() {
        return this.alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Individual getIndividual() {
        return this.individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }
}
