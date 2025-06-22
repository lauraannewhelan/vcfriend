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

    @GetMapping("/pedigrees/{pedigreeId}")
    public List<IndividualDTO> getIndividualsByPedigree(@PathVariable String pedigreeId) {
        return individualService.getByPedigreeId(pedigreeId);
    }

    @GetMapping("/{id}")
    public IndividualDTO getIndividualById(@PathVariable Long id) {
        Individual ind = individualService.getById(id);
        return individualMapper.toDTO(ind);
    }

    @PutMapping("/{id}")
    public void updateIndividual(@PathVariable Long id, @RequestBody IndividualDTO updated) {
        Individual individual = individualService.getById(id);
        if (individual != null) {
            individual.setName(updated.getName());
            individual.setClinicalDiagnosis(updated.getClinicalDiagnosis());
            individual.setDateOfBirth(LocalDate.parse(updated.getDateOfBirth()));
            individual.setProband(updated.getProband());
            individual.setSexLabel(updated.getSexLabel());
            individualService.save(individual);
        }
    }

    @PostMapping("/{id}/upload-vcf")
    public ResponseEntity<String> uploadVCF(@PathVariable Long id,
                                            @RequestParam("file") MultipartFile file) {
        String storagePath = "src/vcf_storage/" + id + ".vcf";
        File dest = new File(storagePath);

        try {
            file.transferTo(dest);
            return ResponseEntity.ok("✅ File uploaded for individual " + id);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("❌ Failed to upload VCF: " + e.getMessage());
        }
    }

    @PostMapping("/relink/{individualId}/{pedigreeId}")
    public void relinkIndividualToPedigree(@PathVariable Long individualId, @PathVariable String pedigreeId) {
        individualService.relinkIndividual(individualId, pedigreeId);
    }

    @GetMapping("/debug")
    public List<Individual> debugAllIndividuals() {
        return individualService.getAllIndividuals();
    }

    @GetMapping("/testrepo/{pedigreeId}")
    public List<Individual> testRepo(@PathVariable String pedigreeId) {
        return individualService.testRepoFindByPedigree(pedigreeId);
    }


    @GetMapping("/{id}/variants")
    public ResponseEntity<List<GenomicVariant>> getVariantsFromDatabase(@PathVariable Long id) {
        System.out.println("🧬 Fetching variants for individual ID: " + id);
        List<GenomicVariant> variants = genomicVariantService.getVariantsByIndividualId(id);

        System.out.println("🔎 Found " + variants.size() + " variants");
        return ResponseEntity.ok(variants);
    }

}
