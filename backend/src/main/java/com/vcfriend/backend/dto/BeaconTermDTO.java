package com.vcfriend.backend.dto;

public class BeaconTermDTO {
    private String id;
    private String label;

    public BeaconTermDTO() {}

    public BeaconTermDTO(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
}
