package com.vcfriend.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "genomic_variant")
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

    // === GETTERS ===

    public Long getId() { return id; }
    public Long getIndividualId() { return individualId; }
    public String getChr() { return chr; }
    public String getStart() { return start; }
    public String getEnd() { return end; }
    public String getRef() { return ref; }
    public String getAlt() { return alt; }
    public String getFuncRefgene() { return funcRefgene; }
    public String getGeneRefgene() { return geneRefgene; }
    public String getGenedetailRefgene() { return genedetailRefgene; }
    public String getExonicfuncRefgene() { return exonicfuncRefgene; }
    public String getAachangeRefgene() { return aachangeRefgene; }
    public String getRevel() { return revel; }
    public String getOmim() { return omim; }
    public String getGt() { return gt; }
    public String getBa1() { return ba1; }
    public String getVariant() { return variant; }
    public String getInheritance() { return inheritance; }
    public String getGnomad40GenomeAf() { return gnomad40GenomeAf; }

    // === SETTERS ===

    public void setIndividualId(Long individualId) { this.individualId = individualId; }
    public void setChr(String chr) { this.chr = chr; }
    public void setStart(String start) { this.start = start; }
    public void setEnd(String end) { this.end = end; }
    public void setRef(String ref) { this.ref = ref; }
    public void setAlt(String alt) { this.alt = alt; }
    public void setFuncRefgene(String funcRefgene) { this.funcRefgene = funcRefgene; }
    public void setGeneRefgene(String geneRefgene) { this.geneRefgene = geneRefgene; }
    public void setGenedetailRefgene(String genedetailRefgene) { this.genedetailRefgene = genedetailRefgene; }
    public void setExonicfuncRefgene(String exonicfuncRefgene) { this.exonicfuncRefgene = exonicfuncRefgene; }
    public void setAachangeRefgene(String aachangeRefgene) { this.aachangeRefgene = aachangeRefgene; }
    public void setRevel(String revel) { this.revel = revel; }
    public void setOmim(String omim) { this.omim = omim; }
    public void setGt(String gt) { this.gt = gt; }
    public void setBa1(String ba1) { this.ba1 = ba1; }
    public void setVariant(String variant) { this.variant = variant; }
    public void setInheritance(String inheritance) { this.inheritance = inheritance; }
    public void setGnomad40GenomeAf(String gnomad40GenomeAf) { this.gnomad40GenomeAf = gnomad40GenomeAf; }
}
