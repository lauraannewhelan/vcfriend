package com.vcfriend.backend.repository;

import com.vcfriend.backend.model.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, Long> {

    // 🔍 Find all individuals by pedigree ID (nested lookup into Pedigree entity)
    List<Individual> findByPedigree_PedigreeId(String pedigreeId);

    // ✅ Optional: Check if an individual exists by ID
    boolean existsById(Long id);

    // ✅ NEW: Check for duplicate study ID
    Optional<Individual> findByStudyId(String studyId);  // 💡 THIS LINE FIXES THE ERROR
}
