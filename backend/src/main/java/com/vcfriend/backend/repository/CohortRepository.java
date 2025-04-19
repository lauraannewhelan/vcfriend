package com.vcfriend.backend.repository;

import com.vcfriend.backend.model.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CohortRepository extends JpaRepository<Cohort, String> {
}
