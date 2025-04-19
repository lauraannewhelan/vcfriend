package com.vcfriend.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Table(name = "analysis")
public class Analysis {

    @Id
    private String id; // matching the VARCHAR PK in DB

    @Column(name = "analysis_id")
    private String analysisId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "individual_id")
    @JsonBackReference
    private Individual individual;

    @Column(name = "biosample_id")
    private String biosampleId;

    private LocalDate analysisDate;
    private String aligner;
    private String variantCaller;
    private String pipelineName;
    private String pipelineRef;
    private String runId;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> info;

    // Getters and setters

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getAnalysisId() { return analysisId; }

    public void setAnalysisId(String analysisId) { this.analysisId = analysisId; }

    public Individual getIndividual() { return individual; }

    public void setIndividual(Individual individual) { this.individual = individual; }

    public String getBiosampleId() { return biosampleId; }

    public void setBiosampleId(String biosampleId) { this.biosampleId = biosampleId; }

    public LocalDate getAnalysisDate() { return analysisDate; }

    public void setAnalysisDate(LocalDate analysisDate) { this.analysisDate = analysisDate; }

    public String getAligner() { return aligner; }

    public void setAligner(String aligner) { this.aligner = aligner; }

    public String getVariantCaller() { return variantCaller; }

    public void setVariantCaller(String variantCaller) { this.variantCaller = variantCaller; }

    public String getPipelineName() { return pipelineName; }

    public void setPipelineName(String pipelineName) { this.pipelineName = pipelineName; }

    public String getPipelineRef() { return pipelineRef; }

    public void setPipelineRef(String pipelineRef) { this.pipelineRef = pipelineRef; }

    public String getRunId() { return runId; }

    public void setRunId(String runId) { this.runId = runId; }

    public Map<String, Object> getInfo() { return info; }

    public void setInfo(Map<String, Object> info) { this.info = info; }
}
