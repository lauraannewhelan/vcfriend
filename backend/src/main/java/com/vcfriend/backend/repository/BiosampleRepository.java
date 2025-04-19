package com.vcfriend.backend.repository;

import com.vcfriend.backend.model.Biosample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiosampleRepository extends JpaRepository<Biosample, String> {
}
