package com.vcfriend.backend.repository;

import com.vcfriend.backend.model.FamilyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<FamilyEntity, Long> {}
