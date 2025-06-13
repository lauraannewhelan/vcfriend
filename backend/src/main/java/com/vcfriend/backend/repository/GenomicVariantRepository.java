package com.vcfriend.backend.repository;

import com.vcfriend.backend.model.GenomicVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenomicVariantRepository extends JpaRepository<GenomicVariant, Long> {

    List<GenomicVariant> findByVariantInternalId(String variantInternalId);

    // ✅ Used to check if *anyone* has this variant (still useful)
    boolean existsByVariantInternalId(String variantInternalId);

    // ✅ Used to check if a *specific individual* already has the variant
    boolean existsByVariantInternalIdAndIndividualId(String variantInternalId, Integer individualId);

    @Query("""
        SELECT gv FROM GenomicVariant gv
        WHERE gv.startPos = :startPos
          AND gv.referenceBases = :referenceBases
          AND gv.alternateBases = :alternateBases
          AND gv.referenceName LIKE CONCAT('%-', :chromosome, '-', :startPos, '-', :referenceBases, '-', :alternateBases)
    """)
    List<GenomicVariant> findByCoordinates(
            @Param("chromosome") String chromosome,
            @Param("startPos") int startPos,
            @Param("referenceBases") String referenceBases,
            @Param("alternateBases") String alternateBases
    );

    // ✅ Return only individuals who have a given variant
    @Query("""
        SELECT gv.individualId FROM GenomicVariant gv
        WHERE gv.startPos = :startPos
          AND gv.referenceBases = :referenceBases
          AND gv.alternateBases = :alternateBases
          AND gv.referenceName LIKE CONCAT('%-', :chromosome, '-', :startPos, '-', :referenceBases, '-', :alternateBases)
    """)
    List<Integer> findIndividualIdsByCoordinates(
            @Param("chromosome") String chromosome,
            @Param("startPos") int startPos,
            @Param("referenceBases") String referenceBases,
            @Param("alternateBases") String alternateBases
    );
}
