package com.vcfriend.backend.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class IndividualDTO {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String clinicalDiagnosis;
    private String proband;

    private BeaconTermDTO sex;
    private BeaconTermDTO ethnicity;
    private BeaconTermDTO geographicOrigin;

    private List<DiseaseDTO> diseases;
    private List<PhenotypicFeatureDTO> phenotypicFeatures;
    private List<TreatmentDTO> treatments;
    private List<ExposureDTO> exposures;
    private List<MeasurementDTO> measures;
    private List<InterventionDTO> interventionsOrProcedures;
    private List<PedigreeDTO> pedigrees;

    private Map<String, Object> info;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getClinicalDiagnosis() {
        return clinicalDiagnosis;
    }

    public void setClinicalDiagnosis(String clinicalDiagnosis) {
        this.clinicalDiagnosis = clinicalDiagnosis;
    }

    public String getProband() {
        return proband;
    }

    public void setProband(String proband) {
        this.proband = proband;
    }

    public BeaconTermDTO getSex() {
        return sex;
    }

    public void setSex(BeaconTermDTO sex) {
        this.sex = sex;
    }

    public BeaconTermDTO getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(BeaconTermDTO ethnicity) {
        this.ethnicity = ethnicity;
    }

    public BeaconTermDTO getGeographicOrigin() {
        return geographicOrigin;
    }

    public void setGeographicOrigin(BeaconTermDTO geographicOrigin) {
        this.geographicOrigin = geographicOrigin;
    }

    public List<DiseaseDTO> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<DiseaseDTO> diseases) {
        this.diseases = diseases;
    }

    public List<PhenotypicFeatureDTO> getPhenotypicFeatures() {
        return phenotypicFeatures;
    }

    public void setPhenotypicFeatures(List<PhenotypicFeatureDTO> phenotypicFeatures) {
        this.phenotypicFeatures = phenotypicFeatures;
    }

    public List<TreatmentDTO> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<TreatmentDTO> treatments) {
        this.treatments = treatments;
    }

    public List<ExposureDTO> getExposures() {
        return exposures;
    }

    public void setExposures(List<ExposureDTO> exposures) {
        this.exposures = exposures;
    }

    public List<MeasurementDTO> getMeasures() {
        return measures;
    }

    public void setMeasures(List<MeasurementDTO> measures) {
        this.measures = measures;
    }

    public List<InterventionDTO> getInterventionsOrProcedures() {
        return interventionsOrProcedures;
    }

    public void setInterventionsOrProcedures(List<InterventionDTO> interventionsOrProcedures) {
        this.interventionsOrProcedures = interventionsOrProcedures;
    }

    public List<PedigreeDTO> getPedigrees() {
        return pedigrees;
    }

    public void setPedigrees(List<PedigreeDTO> pedigrees) {
        this.pedigrees = pedigrees;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
}
