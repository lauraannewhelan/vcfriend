package com.vcfriend.backend.service;

import com.vcfriend.backend.model.GenomicVariant;
import com.vcfriend.backend.repository.GenomicVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenomicVariantService {

    @Autowired
    private GenomicVariantRepository genomicVariantRepository;

    // Method to get genomic variants by variantInternalId
    public List<GenomicVariant> getVariantsByVariantInternalId(String variantInternalId) {
        return genomicVariantRepository.findByVariantInternalId(variantInternalId);
    }
}
