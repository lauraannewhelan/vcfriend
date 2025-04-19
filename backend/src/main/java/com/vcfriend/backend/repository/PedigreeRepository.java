package com.vcfriend.backend.repository;

import com.vcfriend.backend.model.Pedigree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedigreeRepository extends JpaRepository<Pedigree, String> {
    Pedigree findByPedigreeId(String pedigreeId);  // Custom query method to fetch by pedigree_id
}
