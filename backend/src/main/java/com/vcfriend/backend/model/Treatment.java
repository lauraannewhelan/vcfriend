package com.vcfriend.backend.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class Treatment {

    private String ageAtOnset;
    private String cumulativeDose;
    private String doseIntervals;
    private String routeOfAdministration;
    private String schedule;
    private String duration;

    @Embedded
    private BeaconTerm treatmentCode;

    public String getAgeAtOnset() { return ageAtOnset; }
    public void setAgeAtOnset(String ageAtOnset) { this.ageAtOnset = ageAtOnset; }

    public String getCumulativeDose() { return cumulativeDose; }
    public void setCumulativeDose(String cumulativeDose) { this.cumulativeDose = cumulativeDose; }

    public String getDoseIntervals() { return doseIntervals; }
    public void setDoseIntervals(String doseIntervals) { this.doseIntervals = doseIntervals; }

    public String getRouteOfAdministration() { return routeOfAdministration; }
    public void setRouteOfAdministration(String routeOfAdministration) { this.routeOfAdministration = routeOfAdministration; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public BeaconTerm getTreatmentCode() { return treatmentCode; }
    public void setTreatmentCode(BeaconTerm treatmentCode) { this.treatmentCode = treatmentCode; }
}
