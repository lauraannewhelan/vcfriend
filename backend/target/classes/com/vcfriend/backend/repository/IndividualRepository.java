package com.vcfriend.backend.repository;

import com.vcfriend.backend.model.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, Long> {
    List<Individual> findByFamilyId(Long familyId);
}
