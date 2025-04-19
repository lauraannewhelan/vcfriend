package com.vcfriend.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "cohort")
public class Cohort {

    @Id
    private String id;

    private String name;

    @Column(name = "cohort_type")
    private String cohortType;

    @Column(name = "cohort_design_id")
    private String cohortDesignId;

    @Column(name = "cohort_design_label")
    private String cohortDesignLabel;

    @Column(name = "cohort_size")
    private Integer cohortSize;

    @Type(JsonType.class)
    @Column(name = "inclusion_criteria", columnDefinition = "jsonb")
    private Map<String, Object> inclusionCriteria;

    @Type(JsonType.class)
    @Column(name = "exclusion_criteria", columnDefinition = "jsonb")
    private Map<String, Object> exclusionCriteria;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> info;

    @ManyToMany
    @JoinTable(
            name = "cohort_individual",
            joinColumns = @JoinColumn(name = "cohort_id"),
            inverseJoinColumns = @JoinColumn(name = "individual_id")
    )
    @JsonBackReference
    private List<Individual> individuals;

    // Getters and Setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCohortType() { return cohortType; }
    public void setCohortType(String cohortType) { this.cohortType = cohortType; }

    public String getCohortDesignId() { return cohortDesignId; }
    public void setCohortDesignId(String cohortDesignId) { this.cohortDesignId = cohortDesignId; }

    public String getCohortDesignLabel() { return cohortDesignLabel; }
    public void setCohortDesignLabel(String cohortDesignLabel) { this.cohortDesignLabel = cohortDesignLabel; }

    public Integer getCohortSize() { return cohortSize; }
    public void setCohortSize(Integer cohortSize) { this.cohortSize = cohortSize; }

    public Map<String, Object> getInclusionCriteria() { return inclusionCriteria; }
    public void setInclusionCriteria(Map<String, Object> inclusionCriteria) { this.inclusionCriteria = inclusionCriteria; }

    public Map<String, Object> getExclusionCriteria() { return exclusionCriteria; }
    public void setExclusionCriteria(Map<String, Object> exclusionCriteria) { this.exclusionCriteria = exclusionCriteria; }

    public Map<String, Object> getInfo() { return info; }
    public void setInfo(Map<String, Object> info) { this.info = info; }

    public List<Individual> getIndividuals() { return individuals; }
    public void setIndividuals(List<Individual> individuals) { this.individuals = individuals; }
}
