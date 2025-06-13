package com.vcfriend.backend.controller;

import com.vcfriend.backend.model.GenomicVariant;
import com.vcfriend.backend.service.GenomicVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genomic-variants")
@CrossOrigin(origins = "*")
public class GenomicVariantController {

    @Autowired
    private GenomicVariantService genomicVariantService;

    @GetMapping("/search")
    public List<GenomicVariant> searchVariants(@RequestParam String variantInternalId) {
        return genomicVariantService.getVariantsByVariantInternalId(variantInternalId);
    }

    @GetMapping("/search-by-coordinates")
    public List<GenomicVariant> searchByCoordinates(
            @RequestParam String referenceName,
            @RequestParam int startPos,
            @RequestParam String referenceBases,
            @RequestParam String alternateBases) {

        String chromosome = referenceName;
        return genomicVariantService.findByCoordinates(chromosome, startPos, referenceBases, alternateBases);
    }

    // ✅ New endpoint: return a list of individual IDs
    @GetMapping("/individuals-by-variant")
    public List<Integer> getIndividualsForVariant(
            @RequestParam String referenceName,
            @RequestParam int startPos,
            @RequestParam String referenceBases,
            @RequestParam String alternateBases) {

        String chromosome = referenceName;
        return genomicVariantService.findIndividualIdsByCoordinates(chromosome, startPos, referenceBases, alternateBases);
    }
}
