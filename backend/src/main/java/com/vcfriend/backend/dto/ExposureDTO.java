package com.vcfriend.backend.dto;

import java.util.Date;

public class ExposureDTO {
    private BeaconTermDTO exposureCode;
    private String ageAtExposure;
    private Date date;
    private String duration;
    private String value;
    private String unit;

    public BeaconTermDTO getExposureCode() {
        return exposureCode;
    }

    public void setExposureCode(BeaconTermDTO exposureCode) {
        this.exposureCode = exposureCode;
    }

    public String getAgeAtExposure() {
        return ageAtExposure;
    }

    public void setAgeAtExposure(String ageAtExposure) {
        this.ageAtExposure = ageAtExposure;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
