package com.vcfriend.backend.repository;

import com.vcfriend.backend.model.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<Variant, Long> {
}
