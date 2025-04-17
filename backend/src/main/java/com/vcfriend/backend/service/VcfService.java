// File: src/main/java/com/vcfriend/backend/service/VcfService.java

package com.vcfriend.backend.service;

import com.vcfriend.backend.model.Individual;
import com.vcfriend.backend.model.Variant;
import com.vcfriend.backend.repository.IndividualRepository;
import com.vcfriend.backend.repository.VariantRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.Optional;

@Service
public class VcfService {

    @Value("${vcf.upload.dir}")
    private String uploadDir;

    private final IndividualRepository individualRepository;
    private final VariantRepository variantRepository;

    public VcfService(IndividualRepository individualRepository, VariantRepository variantRepository) {
        this.individualRepository = individualRepository;
        this.variantRepository = variantRepository;
    }

    public void saveAndParseVcf(MultipartFile file) throws IOException {
        // Step 1: Save file to disk
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(originalFileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Step 2: Extract individualId from file name
        Long individualId = Long.parseLong(originalFileName.replace(".vcf", ""));
        Optional<Individual> individualOpt = individualRepository.findById(individualId);

        if (individualOpt.isEmpty()) {
            throw new RuntimeException("Individual with ID " + individualId + " not found");
        }

        Individual individual = individualOpt.get();

        // Step 3: Parse and store variants
        parseAndStoreVariants(filePath.toFile(), individual);
    }

    private void parseAndStoreVariants(File vcfFile, Individual individual) {
        try (BufferedReader reader = new BufferedReader(new FileReader(vcfFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) continue;

                String[] fields = line.split("\t");
                if (fields.length < 5) continue;

                String chrom = fields[0];
                int pos = Integer.parseInt(fields[1]);
                String variantId = fields[2];
                String ref = fields[3];
                String alt = fields[4];

                Variant variant = new Variant(chrom, pos, variantId, ref, alt, individual);
                variantRepository.save(variant);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse VCF file: " + e.getMessage());
        }
    }
    // Addition to VcfService.java
    public void saveAndParseVcf(File file) throws IOException {
        String fileName = file.getName();
        Long individualId = Long.parseLong(fileName.replace(".vcf", ""));
        Optional<Individual> individualOpt = individualRepository.findById(individualId);

        if (individualOpt.isEmpty()) {
            throw new RuntimeException("Individual with ID " + individualId + " not found");
        }

        Individual individual = individualOpt.get();
        parseAndStoreVariants(file, individual);
    }

}
