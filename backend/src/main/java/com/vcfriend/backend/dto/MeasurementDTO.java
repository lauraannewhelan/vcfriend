package com.vcfriend.backend.dto;

import java.util.Date;

public class MeasurementDTO {
    private BeaconTermDTO assayCode;
    private String measurementValue;
    private String observationMoment;
    private Date date;
    private String notes;
    private String procedure;

    public BeaconTermDTO getAssayCode() {
        return assayCode;
    }

    public void setAssayCode(BeaconTermDTO assayCode) {
        this.assayCode = assayCode;
    }

    public String getMeasurementValue() {
        return measurementValue;
    }

    public void setMeasurementValue(String measurementValue) {
        this.measurementValue = measurementValue;
    }

    public String getObservationMoment() {
        return observationMoment;
    }

    public void setObservationMoment(String observationMoment) {
        this.observationMoment = observationMoment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
}
