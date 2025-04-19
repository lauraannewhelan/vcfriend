package com.vcfriend.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "individual")
public class Individual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    @JsonBackReference("family-individuals")
    private FamilyEntity family;

    private String name;
    private LocalDate dateOfBirth;
    private String clinicalDiagnosis;

    @Column(length = 1)
    private String proband;  // 'Y' or 'N'

    @OneToMany(mappedBy = "individual", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference("individual-samples")
    private List<Sample> samples;

    // Beacon-compliant fields
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "sex_id")),
            @AttributeOverride(name = "label", column = @Column(name = "sex_label"))
    })
    private BeaconTerm sex;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "ethnicity_id")),
            @AttributeOverride(name = "label", column = @Column(name = "ethnicity_label"))
    })
    private BeaconTerm ethnicity;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "geo_origin_id")),
            @AttributeOverride(name = "label", column = @Column(name = "geo_origin_label"))
    })
    private BeaconTerm geographicOrigin;

    @ElementCollection
    private List<Disease> diseases;

    @ElementCollection
    private List<PhenotypicFeature> phenotypicFeatures;

    @ElementCollection
    private List<Intervention> interventionsOrProcedures;

    @ElementCollection
    private List<Treatment> treatments;

    @ElementCollection
    private List<Exposure> exposures;

    @ElementCollection
    private List<Measurement> measures;

    @ElementCollection
    private List<Pedigree> pedigrees;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> info;

    // Getters and Setters...

    public Long getId() { return id; }

    public FamilyEntity getFamily() { return family; }

    public void setFamily(FamilyEntity family) { this.family = family; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getClinicalDiagnosis() { return clinicalDiagnosis; }

    public void setClinicalDiagnosis(String clinicalDiagnosis) { this.clinicalDiagnosis = clinicalDiagnosis; }

    public String getProband() { return proband; }

    public void setProband(String proband) { this.proband = proband; }

    public List<Sample> getSamples() { return samples; }

    public void setSamples(List<Sample> samples) { this.samples = samples; }

    public BeaconTerm getSex() { return sex; }

    public void setSex(BeaconTerm sex) { this.sex = sex; }

    public BeaconTerm getEthnicity() { return ethnicity; }

    public void setEthnicity(BeaconTerm ethnicity) { this.ethnicity = ethnicity; }

    public BeaconTerm getGeographicOrigin() { return geographicOrigin; }

    public void setGeographicOrigin(BeaconTerm geographicOrigin) { this.geographicOrigin = geographicOrigin; }

    public List<Disease> getDiseases() { return diseases; }

    public void setDiseases(List<Disease> diseases) { this.diseases = diseases; }

    public List<PhenotypicFeature> getPhenotypicFeatures() { return phenotypicFeatures; }

    public void setPhenotypicFeatures(List<PhenotypicFeature> phenotypicFeatures) {
        this.phenotypicFeatures = phenotypicFeatures;
    }

    public List<Intervention> getInterventionsOrProcedures() { return interventionsOrProcedures; }

    public void setInterventionsOrProcedures(List<Intervention> interventionsOrProcedures) {
        this.interventionsOrProcedures = interventionsOrProcedures;
    }

    public List<Treatment> getTreatments() { return treatments; }

    public void setTreatments(List<Treatment> treatments) { this.treatments = treatments; }

    public List<Exposure> getExposures() { return exposures; }

    public void setExposures(List<Exposure> exposures) { this.exposures = exposures; }

    public List<Measurement> getMeasures() { return measures; }

    public void setMeasures(List<Measurement> measures) { this.measures = measures; }

    public List<Pedigree> getPedigrees() { return pedigrees; }

    public void setPedigrees(List<Pedigree> pedigrees) { this.pedigrees = pedigrees; }

    public Map<String, Object> getInfo() { return info; }

    public void setInfo(Map<String, Object> info) { this.info = info; }
}
