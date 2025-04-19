package com.vcfriend.backend.dto;

import java.util.Date;

public class InterventionDTO {
    private BeaconTermDTO procedureCode;
    private Date dateOfProcedure;
    private String ageAtProcedure;
    private String bodySite;

    public BeaconTermDTO getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(BeaconTermDTO procedureCode) {
        this.procedureCode = procedureCode;
    }

    public Date getDateOfProcedure() {
        return dateOfProcedure;
    }

    public void setDateOfProcedure(Date dateOfProcedure) {
        this.dateOfProcedure = dateOfProcedure;
    }

    public String getAgeAtProcedure() {
        return ageAtProcedure;
    }

    public void setAgeAtProcedure(String ageAtProcedure) {
        this.ageAtProcedure = ageAtProcedure;
    }

    public String getBodySite() {
        return bodySite;
    }

    public void setBodySite(String bodySite) {
        this.bodySite = bodySite;
    }
}
