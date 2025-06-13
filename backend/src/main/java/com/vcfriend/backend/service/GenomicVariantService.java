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

    public List<GenomicVariant> getVariantsByVariantInternalId(String variantInternalId) {
        return genomicVariantRepository.findByVariantInternalId(variantInternalId);
    }

    public List<GenomicVariant> findByCoordinates(String chromosome, int startPos, String referenceBases, String alternateBases) {
        return genomicVariantRepository.findByCoordinates(chromosome, startPos, referenceBases, alternateBases);
    }

    // ✅ New method to return list of individual IDs
    public List<Integer> findIndividualIdsByCoordinates(String chromosome, int startPos, String referenceBases, String alternateBases) {
        return genomicVariantRepository.findIndividualIdsByCoordinates(chromosome, startPos, referenceBases, alternateBases);
    }
}
