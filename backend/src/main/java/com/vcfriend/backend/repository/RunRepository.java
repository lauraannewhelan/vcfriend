package com.vcfriend.backend.repository;

import com.vcfriend.backend.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunRepository extends JpaRepository<Run, String> {
}
