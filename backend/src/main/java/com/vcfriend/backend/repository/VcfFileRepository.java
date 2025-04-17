package com.vcfriend.backend.repository;

import com.vcfriend.backend.model.VcfFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VcfFileRepository extends JpaRepository<VcfFile, Long> {}
