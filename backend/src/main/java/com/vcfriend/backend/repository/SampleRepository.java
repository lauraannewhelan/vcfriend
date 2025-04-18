package com.vcfriend.backend.repository;

import com.vcfriend.backend.model.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long> {}
