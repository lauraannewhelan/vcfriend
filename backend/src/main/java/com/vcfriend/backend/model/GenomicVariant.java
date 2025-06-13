package com.vcfriend.backend.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Map;

@Entity
@Table(
        name = "genomic_variant",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"variant_internal_id", "individual_id"})
        }
)
public class GenomicVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "variant_internal_id")
    private String variantInternalId;

    private String referenceName;
    private Integer startPos;
    private Integer endPos;
    private String referenceBases;
    private String alternateBases;
    private String variantType;

    @ElementCollection
    private List<String> geneIds;

    @ElementCollection
    private List<String> molecularEffects;

    @ElementCollection
    private List<String> aminoacidChanges;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> genomicFeatures;

    private String clinvarVariantId;
    private String genomicHgvsId;

    @ElementCollection
    private List<String> proteinHgvsIds;

    @ElementCollection
    private List<String> transcriptHgvsIds;

    @ElementCollection
    private List<String> variantAlternativeIds;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> clinicalInterpretations;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> phenotypicEffects;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> frequencyInPopulations;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> info;

    // ✅ FIXED — now explicitly mapped to match the DB column
    @Column(name = "individual_id")
    private Integer individualId;
    private String biosampleId;
    private String runId;
    private String analysisId;
    private String datasetId;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getVariantInternalId() {
        return variantInternalId;
    }

    public void setVariantInternalId(String variantInternalId) {
        this.variantInternalId = variantInternalId;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public Integer getStartPos() {
        return startPos;
    }

    public void setStartPos(Integer startPos) {
        this.startPos = startPos;
    }

    public Integer getEndPos() {
        return endPos;
    }

    public void setEndPos(Integer endPos) {
        this.endPos = endPos;
    }

    public String getReferenceBases() {
        return referenceBases;
    }

    public void setReferenceBases(String referenceBases) {
        this.referenceBases = referenceBases;
    }

    public String getAlternateBases() {
        return alternateBases;
    }

    public void setAlternateBases(String alternateBases) {
        this.alternateBases = alternateBases;
    }

    public String getVariantType() {
        return variantType;
    }

    public void setVariantType(String variantType) {
        this.variantType = variantType;
    }

    public List<String> getGeneIds() {
        return geneIds;
    }

    public void setGeneIds(List<String> geneIds) {
        this.geneIds = geneIds;
    }

    public List<String> getMolecularEffects() {
        return molecularEffects;
    }

    public void setMolecularEffects(List<String> molecularEffects) {
        this.molecularEffects = molecularEffects;
    }

    public List<String> getAminoacidChanges() {
        return aminoacidChanges;
    }

    public void setAminoacidChanges(List<String> aminoacidChanges) {
        this.aminoacidChanges = aminoacidChanges;
    }

    public Map<String, Object> getGenomicFeatures() {
        return genomicFeatures;
    }

    public void setGenomicFeatures(Map<String, Object> genomicFeatures) {
        this.genomicFeatures = genomicFeatures;
    }

    public String getClinvarVariantId() {
        return clinvarVariantId;
    }

    public void setClinvarVariantId(String clinvarVariantId) {
        this.clinvarVariantId = clinvarVariantId;
    }

    public String getGenomicHgvsId() {
        return genomicHgvsId;
    }

    public void setGenomicHgvsId(String genomicHgvsId) {
        this.genomicHgvsId = genomicHgvsId;
    }

    public List<String> getProteinHgvsIds() {
        return proteinHgvsIds;
    }

    public void setProteinHgvsIds(List<String> proteinHgvsIds) {
        this.proteinHgvsIds = proteinHgvsIds;
    }

    public List<String> getTranscriptHgvsIds() {
        return transcriptHgvsIds;
    }

    public void setTranscriptHgvsIds(List<String> transcriptHgvsIds) {
        this.transcriptHgvsIds = transcriptHgvsIds;
    }

    public List<String> getVariantAlternativeIds() {
        return variantAlternativeIds;
    }

    public void setVariantAlternativeIds(List<String> variantAlternativeIds) {
        this.variantAlternativeIds = variantAlternativeIds;
    }

    public Map<String, Object> getClinicalInterpretations() {
        return clinicalInterpretations;
    }

    public void setClinicalInterpretations(Map<String, Object> clinicalInterpretations) {
        this.clinicalInterpretations = clinicalInterpretations;
    }

    public Map<String, Object> getPhenotypicEffects() {
        return phenotypicEffects;
    }

    public void setPhenotypicEffects(Map<String, Object> phenotypicEffects) {
        this.phenotypicEffects = phenotypicEffects;
    }

    public Map<String, Object> getFrequencyInPopulations() {
        return frequencyInPopulations;
    }

    public void setFrequencyInPopulations(Map<String, Object> frequencyInPopulations) {
        this.frequencyInPopulations = frequencyInPopulations;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

    public Integer getIndividualId() {
        return individualId;
    }

    public void setIndividualId(Integer individualId) {
        this.individualId = individualId;
    }

    public String getBiosampleId() {
        return biosampleId;
    }

    public void setBiosampleId(String biosampleId) {
        this.biosampleId = biosampleId;
    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public String getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(String analysisId) {
        this.analysisId = analysisId;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }
}
