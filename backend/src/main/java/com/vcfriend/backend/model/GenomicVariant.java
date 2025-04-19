package com.vcfriend.backend.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "genomic_variant")
public class GenomicVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "variant_internal_id", unique = true)
    private String variantInternalId;

    private String referenceName;
    private Integer startPos;
    private Integer endPos;
    private String referenceBases;
    private String alternateBases;
    private String variantType;

    @ElementCollection
    private List<String> geneIds;

    @ElementCollection
    private List<String> molecularEffects;

    @ElementCollection
    private List<String> aminoacidChanges;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> genomicFeatures;

    private String clinvarVariantId;
    private String genomicHgvsId;

    @ElementCollection
    private List<String> proteinHgvsIds;

    @ElementCollection
    private List<String> transcriptHgvsIds;

    @ElementCollection
    private List<String> variantAlternativeIds;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> clinicalInterpretations;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> phenotypicEffects;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> frequencyInPopulations;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> info;

    private Integer individualId;
    private String biosampleId;
    private String runId;
    private String analysisId;
    private String datasetId;

    // Getters and Setters...

    // (Same as you already have, just adjust the types of the JSON fields if you copied them from the previous version.)
}
