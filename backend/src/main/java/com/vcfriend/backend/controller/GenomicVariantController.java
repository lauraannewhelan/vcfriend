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

    // Endpoint to search genomic variants by variantInternalId
    @GetMapping("/search")
    public List<GenomicVariant> searchVariants(@RequestParam String variantInternalId) {
        return genomicVariantService.getVariantsByVariantInternalId(variantInternalId);
    }
}
