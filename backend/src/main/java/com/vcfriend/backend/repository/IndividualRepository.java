package com.vcfriend.backend.repository;

import com.vcfriend.backend.model.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, Long> {

    // ✅ This is the key method you're missing
    List<Individual> findByPedigree_PedigreeId(String pedigreeId);
}
