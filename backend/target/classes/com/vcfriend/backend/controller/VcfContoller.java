//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.vcfriend.backend.controller;

import com.vcfriend.backend.service.VcfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/api/vcf"})
public class VcfController {
    private final VcfService vcfService;

    @Autowired
    public VcfController(VcfService vcfService) {
        this.vcfService = vcfService;
    }

    @PostMapping({"/upload"})
    public ResponseEntity<String> uploadVcfFile(@RequestParam("file") MultipartFile file) {
        try {
            this.vcfService.saveAndParseVcf(file);
            return ResponseEntity.ok("VCF file uploaded and processed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing VCF file: " + e.getMessage());
        }
    }
}
