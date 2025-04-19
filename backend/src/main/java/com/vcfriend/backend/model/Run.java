package com.vcfriend.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Table(name = "run")
public class Run {

    @Id
    private String id;  // E.g., "SRR10903401"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "individual_id")
    private Individual individual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "biosample_id")
    private Biosample biosample;

    private LocalDate runDate;

    private String libraryLayout;
    private String librarySelection;
    private String libraryStrategy;

    private String librarySourceId;
    private String librarySourceLabel;

    private String platform;
    private String platformModelId;
    private String platformModelLabel;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> info;

    // Getters and Setters

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public Individual getIndividual() { return individual; }

    public void setIndividual(Individual individual) { this.individual = individual; }

    public Biosample getBiosample() { return biosample; }

    public void setBiosample(Biosample biosample) { this.biosample = biosample; }

    public LocalDate getRunDate() { return runDate; }

    public void setRunDate(LocalDate runDate) { this.runDate = runDate; }

    public String getLibraryLayout() { return libraryLayout; }

    public void setLibraryLayout(String libraryLayout) { this.libraryLayout = libraryLayout; }

    public String getLibrarySelection() { return librarySelection; }

    public void setLibrarySelection(String librarySelection) { this.librarySelection = librarySelection; }

    public String getLibraryStrategy() { return libraryStrategy; }

    public void setLibraryStrategy(String libraryStrategy) { this.libraryStrategy = libraryStrategy; }

    public String getLibrarySourceId() { return librarySourceId; }

    public void setLibrarySourceId(String librarySourceId) { this.librarySourceId = librarySourceId; }

    public String getLibrarySourceLabel() { return librarySourceLabel; }

    public void setLibrarySourceLabel(String librarySourceLabel) { this.librarySourceLabel = librarySourceLabel; }

    public String getPlatform() { return platform; }

    public void setPlatform(String platform) { this.platform = platform; }

    public String getPlatformModelId() { return platformModelId; }

    public void setPlatformModelId(String platformModelId) { this.platformModelId = platformModelId; }

    public String getPlatformModelLabel() { return platformModelLabel; }

    public void setPlatformModelLabel(String platformModelLabel) { this.platformModelLabel = platformModelLabel; }

    public Map<String, Object> getInfo() { return info; }

    public void setInfo(Map<String, Object> info) { this.info = info; }
}
