package com.vcfriend.backend.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class BeaconTerm {
    private String id;
    private String label;

    public BeaconTerm() {}

    public BeaconTerm(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
