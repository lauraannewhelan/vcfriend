package com.vcfriend.backend.repository;

import com.vcfriend.backend.model.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasetRepository extends JpaRepository<Dataset, String> {
}
