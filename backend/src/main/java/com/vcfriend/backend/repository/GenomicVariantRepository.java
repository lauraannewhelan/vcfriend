package com.vcfriend.backend.repository;

import com.vcfriend.backend.model.GenomicVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenomicVariantRepository extends JpaRepository<GenomicVariant, Long> {

    List<GenomicVariant> findByGeneRefgeneContainingIgnoreCase(String geneRefgene);
    List<GenomicVariant> findByVariantContainingIgnoreCase(String variant);
    List<GenomicVariant> findByInheritanceContainingIgnoreCase(String inheritance);
    List<GenomicVariant> findByChr(String chr);
    List<GenomicVariant> findByFuncRefgeneContainingIgnoreCase(String func);

    // ✅ Only keep this one
    List<GenomicVariant> findByIndividualId(Long individualId);


}
