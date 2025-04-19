package com.vcfriend.backend.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class PhenotypicFeature {

    private String onset;
    private String resolution;
    private String severity;
    private String modifiers;
    private Boolean excluded;
    private String evidence;
    private String notes;

    @Embedded
    private BeaconTerm featureType;

    public String getOnset() { return onset; }
    public void setOnset(String onset) { this.onset = onset; }

    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getModifiers() { return modifiers; }
    public void setModifiers(String modifiers) { this.modifiers = modifiers; }

    public Boolean getExcluded() { return excluded; }
    public void setExcluded(Boolean excluded) { this.excluded = excluded; }

    public String getEvidence() { return evidence; }
    public void setEvidence(String evidence) { this.evidence = evidence; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public BeaconTerm getFeatureType() { return featureType; }
    public void setFeatureType(BeaconTerm featureType) { this.featureType = featureType; }
}
