package com.vcfriend.backend.controller;

import com.vcfriend.backend.dto.IndividualDTO;
import com.vcfriend.backend.mapper.IndividualMapper;
import com.vcfriend.backend.model.GenomicVariant;
import com.vcfriend.backend.model.Individual;
import com.vcfriend.backend.service.GenomicVariantService;
import com.vcfriend.backend.service.IndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/individuals")
@CrossOrigin(origins = "http://localhost:3000")
public class IndividualController {

    @Autowired
    private IndividualService individualService;

    @Autowired
    private IndividualMapper individualMapper;

    @Autowired
    private GenomicVariantService genomicVariantService;

    // Get all individuals for a given pedigree
    @GetMapping("/pedigrees/{pedigreeId}")
    public List<IndividualDTO> getIndividualsByPedigree(@PathVariable String pedigreeId) {
        return individualService.getByPedigreeId(pedigreeId);
    }

    // Get a specific individual by ID
    @GetMapping("/{id}")
    public IndividualDTO getIndividualById(@PathVariable Long id) {
        Individual ind = individualService.getById(id);
        return individualMapper.toDTO(ind);
    }

    // Update an existing individual
    @PutMapping("/{id}")
    public void updateIndividual(@PathVariable Long id, @RequestBody IndividualDTO updated) {
        Individual individual = individualService.getById(id);
        if (individual != null) {
            individual.setClinicalDiagnosis(updated.getClinicalDiagnosis());
            individual.setDateOfBirth(LocalDate.parse(updated.getDateOfBirth()));
            individual.setProband(updated.getProband());
            individual.setSexLabel(updated.getSexLabel());
            individual.setStudyId(updated.getStudyId());
            individualService.save(individual);
        }
    }

    // Create a new individual
    @PostMapping
    public ResponseEntity<IndividualDTO> createIndividual(@RequestBody IndividualDTO dto) {
        Individual saved = individualService.createFromDTO(dto);
        return ResponseEntity.status(201).body(individualMapper.toDTO(saved));
    }

    // Upload CSV to /vcf_storage
    @PostMapping("/upload-csv")
    public ResponseEntity<String> uploadCSV(@RequestParam("csv") MultipartFile file) {
        String storagePath = System.getProperty("user.dir") + "/vcf_storage/" + file.getOriginalFilename();
        File dest = new File(storagePath);
        dest.getParentFile().mkdirs(); // Create dir if missing

        try {
            file.transferTo(dest);
            System.out.println("📄 CSV uploaded to: " + dest.getAbsolutePath());
            return ResponseEntity.ok("✅ CSV uploaded to: " + dest.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ CSV upload failed: " + e.getMessage());
        }
    }

    // Upload VCF file for a specific individual
    @PostMapping("/{id}/upload-vcf")
    public ResponseEntity<String> uploadVCF(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String storagePath = System.getProperty("user.dir") + "/vcf_storage/" + id + ".vcf";
        File dest = new File(storagePath);
        dest.getParentFile().mkdirs();

        try {
            file.transferTo(dest);
            return ResponseEntity.ok("✅ VCF uploaded for individual " + id);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ VCF upload failed: " + e.getMessage());
        }
    }

    // Relink an individual to a different pedigree
    @PostMapping("/relink/{individualId}/{pedigreeId}")
    public void relinkIndividualToPedigree(@PathVariable Long individualId, @PathVariable String pedigreeId) {
        individualService.relinkIndividual(individualId, pedigreeId);
    }

    // Get variants for an individual
    @GetMapping("/{id}/variants")
    public ResponseEntity<List<GenomicVariant>> getVariantsFromDatabase(@PathVariable Long id) {
        System.out.println("📥 API hit: /variants for individual ID " + id);
        List<GenomicVariant> variants = genomicVariantService.getVariantsByIndividualId(id);
        System.out.println("📤 Returning " + variants.size() + " variants");
        return ResponseEntity.ok(variants);
    }


    // Get individual by study ID
    @GetMapping("/study-id/{studyId}")
    public ResponseEntity<IndividualDTO> getByStudyId(@PathVariable String studyId) {
        Individual individual = individualService.getByStudyId(studyId);
        if (individual != null) {
            return ResponseEntity.ok(individualMapper.toDTO(individual));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
