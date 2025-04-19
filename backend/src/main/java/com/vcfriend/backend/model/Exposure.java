package com.vcfriend.backend.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import java.util.Date;

@Embeddable
public class Exposure {

    private Date date;
    private String ageAtExposure;
    private String duration;
    private String value;
    private String unit;

    @Embedded
    private BeaconTerm exposureCode;

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getAgeAtExposure() { return ageAtExposure; }
    public void setAgeAtExposure(String ageAtExposure) { this.ageAtExposure = ageAtExposure; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public BeaconTerm getExposureCode() { return exposureCode; }
    public void setExposureCode(BeaconTerm exposureCode) { this.exposureCode = exposureCode; }
}
