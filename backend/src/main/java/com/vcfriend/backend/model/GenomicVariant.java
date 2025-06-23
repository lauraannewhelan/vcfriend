package com.vcfriend.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "genomic_variant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenomicVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "individual_id")
    private Long individualId;

    @Column(name = "Chr")
    private String chr;

    @Column(name = "Start")
    private String start;

    @Column(name = "\"End\"")
    private String end;

    @Column(name = "Ref")
    private String ref;

    @Column(name = "Alt")
    private String alt;

    @Column(name = "Func.refGene")
    private String funcRefgene;

    @Column(name = "Gene.refGene")
    private String geneRefgene;

    @Column(name = "GeneDetail.refGene", columnDefinition = "TEXT")
    private String genedetailRefgene;

    @Column(name = "ExonicFunc.refGene")
    private String exonicfuncRefgene;

    @Column(name = "AAChange.refGene", columnDefinition = "TEXT")
    private String aachangeRefgene;

    @Column(name = "REVEL")
    private String revel;

    @Column(name = "OMIM", columnDefinition = "TEXT")
    private String omim;

    @Column(name = "GT")
    private String gt;

    @Column(name = "BA1")
    private String ba1;

    @Column(name = "variant", columnDefinition = "TEXT")
    private String variant;

    @Column(name = "Inheritance")
    private String inheritance;

    @Column(name = "gnomad40_genome_AF")
    private String gnomad40GenomeAf;
}
