package com.vcfriend.backend.controller;

import com.vcfriend.backend.service.VcfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/vcf")
@CrossOrigin(origins = "*")
public class VcfController {

    private final VcfService vcfService;

    @Autowired
    public VcfController(VcfService vcfService) {
        this.vcfService = vcfService;
    }

    // Part 1: Upload VCF Endpoint
    @PostMapping("/upload")
    public ResponseEntity<String> uploadVcfFile(@RequestParam("file") MultipartFile file) {
        try {
            vcfService.saveAndParseVcf(file);
            return ResponseEntity.ok("VCF file uploaded and processed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing VCF file: " + e.getMessage());
        }
    }
}
