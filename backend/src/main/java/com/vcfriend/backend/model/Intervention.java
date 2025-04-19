package com.vcfriend.backend.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.util.Date;

@Embeddable
public class Intervention {

    private Date dateOfProcedure;
    private String ageAtProcedure;
    private String bodySite;

    @Embedded
    private BeaconTerm procedureCode;

    public Date getDateOfProcedure() { return dateOfProcedure; }
    public void setDateOfProcedure(Date dateOfProcedure) { this.dateOfProcedure = dateOfProcedure; }

    public String getAgeAtProcedure() { return ageAtProcedure; }
    public void setAgeAtProcedure(String ageAtProcedure) { this.ageAtProcedure = ageAtProcedure; }

    public String getBodySite() { return bodySite; }
    public void setBodySite(String bodySite) { this.bodySite = bodySite; }

    public BeaconTerm getProcedureCode() { return procedureCode; }
    public void setProcedureCode(BeaconTerm procedureCode) { this.procedureCode = procedureCode; }
}
