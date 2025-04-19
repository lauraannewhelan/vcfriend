package com.vcfriend.backend.repository;

import com.vcfriend.backend.model.GenomicVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GenomicVariantRepository extends JpaRepository<GenomicVariant, Long> {

    // Method to find GenomicVariants based on variantInternalId
    List<GenomicVariant> findByVariantInternalId(String variantInternalId);

    // Other methods can be added if necessary
}
