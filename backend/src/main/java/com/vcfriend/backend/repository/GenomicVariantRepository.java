package com.vcfriend.backend.repository;

import com.vcfriend.backend.model.GenomicVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenomicVariantRepository extends JpaRepository<GenomicVariant, Long> {
}
