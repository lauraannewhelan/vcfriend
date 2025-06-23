package com.vcfriend.backend.controller;

import com.vcfriend.backend.model.GenomicVariant;
import com.vcfriend.backend.service.GenomicVariantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/variants")
public class GenomicVariantController {

    private final GenomicVariantService variantService;

    // ✅ Manual constructor since Lombok is not working
    public GenomicVariantController(GenomicVariantService variantService) {
        this.variantService = variantService;
    }

    @GetMapping
    public ResponseEntity<List<GenomicVariant>> getAllVariants() {
        return ResponseEntity.ok(variantService.getAllVariants());
    }

    @GetMapping("/gene")
    public ResponseEntity<List<GenomicVariant>> getVariantsByGene(@RequestParam String gene) {
        return ResponseEntity.ok(variantService.getVariantsByGene(gene));
    }

    @GetMapping("/function")
    public ResponseEntity<List<GenomicVariant>> getVariantsByFunction(@RequestParam String func) {
        return ResponseEntity.ok(variantService.getVariantsByFunc(func));
    }

    @GetMapping("/individual")
    public ResponseEntity<List<GenomicVariant>> getVariantsByParticipantId(@RequestParam Integer id) {
        return ResponseEntity.ok(variantService.getVariantsByIndividualId(Long.valueOf(id)));
    }

    @GetMapping("/search-by-variant")
    public ResponseEntity<List<GenomicVariant>> searchByVariant(@RequestParam String variant) {
        List<GenomicVariant> matches = variantService.getVariantsByVariantString(variant);
        if (matches.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());
        }
        return ResponseEntity.ok(matches);
    }

    @PostMapping("/upload/csv")
    public ResponseEntity<String> uploadAnnotatedCsv(@RequestParam("file") MultipartFile file) {
        try {
            variantService.processAnnotatedCsv(file);
            return ResponseEntity.ok("Annotated CSV uploaded and processed successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to process CSV: " + e.getMessage());
        }
    }
}
