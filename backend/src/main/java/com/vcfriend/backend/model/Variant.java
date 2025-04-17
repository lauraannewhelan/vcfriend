package com.vcfriend.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import java.util.Objects;

@Entity
@Table(name = "variant")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chrom;
    private Integer pos;
    private String ref;
    private String alt;
    private String qual;
    private String filter;

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private Object info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vcf_file_id")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private VcfFile vcfFile;

    // Getters and setters omitted for brevity (keep yours as-is)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getChrom() { return chrom; }
    public void setChrom(String chrom) { this.chrom = chrom; }

    public Integer getPos() { return pos; }
    public void setPos(Integer pos) { this.pos = pos; }

    public String getRef() { return ref; }
    public void setRef(String ref) { this.ref = ref; }

    public String getAlt() { return alt; }
    public void setAlt(String alt) { this.alt = alt; }

    public String getQual() { return qual; }
    public void setQual(String qual) { this.qual = qual; }

    public String getFilter() { return filter; }
    public void setFilter(String filter) { this.filter = filter; }

    public Object getInfo() { return info; }
    public void setInfo(Object info) { this.info = info; }

    public VcfFile getVcfFile() { return vcfFile; }
    public void setVcfFile(VcfFile vcfFile) { this.vcfFile = vcfFile; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Variant)) return false;
        Variant variant = (Variant) o;
        return Objects.equals(id, variant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
