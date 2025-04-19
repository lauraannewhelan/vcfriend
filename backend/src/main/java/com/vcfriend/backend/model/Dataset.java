package com.vcfriend.backend.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "dataset")
public class Dataset {

    @Id
    private String id;

    private String name;
    private String description;
    private String version;

    @Column(name = "create_datetime")
    private LocalDateTime createDateTime;

    @Column(name = "update_datetime")
    private LocalDateTime updateDateTime;

    @Column(name = "external_url")
    private String externalUrl;

    @Type(JsonType.class)
    @Column(name = "data_use_conditions", columnDefinition = "jsonb")
    private Map<String, Object> dataUseConditions;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> info;

    @ManyToMany
    @JoinTable(
            name = "dataset_individual",
            joinColumns = @JoinColumn(name = "dataset_id"),
            inverseJoinColumns = @JoinColumn(name = "individual_id")
    )
    private Set<Individual> individuals;

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public Map<String, Object> getDataUseConditions() {
        return dataUseConditions;
    }

    public void setDataUseConditions(Map<String, Object> dataUseConditions) {
        this.dataUseConditions = dataUseConditions;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

    public Set<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(Set<Individual> individuals) {
        this.individuals = individuals;
    }
}
