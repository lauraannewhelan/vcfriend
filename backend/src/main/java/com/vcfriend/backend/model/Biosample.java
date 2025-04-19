package com.vcfriend.backend.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Table(name = "biosample")
public class Biosample {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "individual_id")
    private Individual individual;

    private LocalDate collectionDate;
    private String collectionMoment;
    private String notes;

    private String biosampleStatusId;
    private String biosampleStatusLabel;

    private String histologicalDiagnosisId;
    private String histologicalDiagnosisLabel;

    private String obtentionProcedureId;
    private String obtentionProcedureLabel;
    private LocalDate obtentionDate;
    private String obtentionBodySite;

    private String pathologicalStageId;
    private String pathologicalStageLabel;

    private String tumorGradeId;
    private String tumorGradeLabel;

    private String tumorProgressionId;
    private String tumorProgressionLabel;

    private String sampleOriginTypeId;
    private String sampleOriginTypeLabel;

    private String sampleOriginDetailId;
    private String sampleOriginDetailLabel;

    private String sampleProcessingId;
    private String sampleProcessingLabel;

    private String sampleStorageId;
    private String sampleStorageLabel;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> info;

    // Getters and Setters...

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Individual getIndividual() { return individual; }
    public void setIndividual(Individual individual) { this.individual = individual; }

    public LocalDate getCollectionDate() { return collectionDate; }
    public void setCollectionDate(LocalDate collectionDate) { this.collectionDate = collectionDate; }

    public String getCollectionMoment() { return collectionMoment; }
    public void setCollectionMoment(String collectionMoment) { this.collectionMoment = collectionMoment; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getBiosampleStatusId() { return biosampleStatusId; }
    public void setBiosampleStatusId(String biosampleStatusId) { this.biosampleStatusId = biosampleStatusId; }

    public String getBiosampleStatusLabel() { return biosampleStatusLabel; }
    public void setBiosampleStatusLabel(String biosampleStatusLabel) { this.biosampleStatusLabel = biosampleStatusLabel; }

    public String getHistologicalDiagnosisId() { return histologicalDiagnosisId; }
    public void setHistologicalDiagnosisId(String histologicalDiagnosisId) { this.histologicalDiagnosisId = histologicalDiagnosisId; }

    public String getHistologicalDiagnosisLabel() { return histologicalDiagnosisLabel; }
    public void setHistologicalDiagnosisLabel(String histologicalDiagnosisLabel) { this.histologicalDiagnosisLabel = histologicalDiagnosisLabel; }

    public String getObtentionProcedureId() { return obtentionProcedureId; }
    public void setObtentionProcedureId(String obtentionProcedureId) { this.obtentionProcedureId = obtentionProcedureId; }

    public String getObtentionProcedureLabel() { return obtentionProcedureLabel; }
    public void setObtentionProcedureLabel(String obtentionProcedureLabel) { this.obtentionProcedureLabel = obtentionProcedureLabel; }

    public LocalDate getObtentionDate() { return obtentionDate; }
    public void setObtentionDate(LocalDate obtentionDate) { this.obtentionDate = obtentionDate; }

    public String getObtentionBodySite() { return obtentionBodySite; }
    public void setObtentionBodySite(String obtentionBodySite) { this.obtentionBodySite = obtentionBodySite; }

    public String getPathologicalStageId() { return pathologicalStageId; }
    public void setPathologicalStageId(String pathologicalStageId) { this.pathologicalStageId = pathologicalStageId; }

    public String getPathologicalStageLabel() { return pathologicalStageLabel; }
    public void setPathologicalStageLabel(String pathologicalStageLabel) { this.pathologicalStageLabel = pathologicalStageLabel; }

    public String getTumorGradeId() { return tumorGradeId; }
    public void setTumorGradeId(String tumorGradeId) { this.tumorGradeId = tumorGradeId; }

    public String getTumorGradeLabel() { return tumorGradeLabel; }
    public void setTumorGradeLabel(String tumorGradeLabel) { this.tumorGradeLabel = tumorGradeLabel; }

    public String getTumorProgressionId() { return tumorProgressionId; }
    public void setTumorProgressionId(String tumorProgressionId) { this.tumorProgressionId = tumorProgressionId; }

    public String getTumorProgressionLabel() { return tumorProgressionLabel; }
    public void setTumorProgressionLabel(String tumorProgressionLabel) { this.tumorProgressionLabel = tumorProgressionLabel; }

    public String getSampleOriginTypeId() { return sampleOriginTypeId; }
    public void setSampleOriginTypeId(String sampleOriginTypeId) { this.sampleOriginTypeId = sampleOriginTypeId; }

    public String getSampleOriginTypeLabel() { return sampleOriginTypeLabel; }
    public void setSampleOriginTypeLabel(String sampleOriginTypeLabel) { this.sampleOriginTypeLabel = sampleOriginTypeLabel; }

    public String getSampleOriginDetailId() { return sampleOriginDetailId; }
    public void setSampleOriginDetailId(String sampleOriginDetailId) { this.sampleOriginDetailId = sampleOriginDetailId; }

    public String getSampleOriginDetailLabel() { return sampleOriginDetailLabel; }
    public void setSampleOriginDetailLabel(String sampleOriginDetailLabel) { this.sampleOriginDetailLabel = sampleOriginDetailLabel; }

    public String getSampleProcessingId() { return sampleProcessingId; }
    public void setSampleProcessingId(String sampleProcessingId) { this.sampleProcessingId = sampleProcessingId; }

    public String getSampleProcessingLabel() { return sampleProcessingLabel; }
    public void setSampleProcessingLabel(String sampleProcessingLabel) { this.sampleProcessingLabel = sampleProcessingLabel; }

    public String getSampleStorageId() { return sampleStorageId; }
    public void setSampleStorageId(String sampleStorageId) { this.sampleStorageId = sampleStorageId; }

    public String getSampleStorageLabel() { return sampleStorageLabel; }
    public void setSampleStorageLabel(String sampleStorageLabel) { this.sampleStorageLabel = sampleStorageLabel; }

    public Map<String, Object> getInfo() { return info; }
    public void setInfo(Map<String, Object> info) { this.info = info; }
}
