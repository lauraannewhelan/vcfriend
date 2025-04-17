package com.vcfriend.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vcfriend.backend.model.*;
import com.vcfriend.backend.repository.*;
import htsjdk.variant.variantcontext.VariantContext;
import htsjdk.variant.vcf.VCFFileReader;
import htsjdk.variant.vcf.VCFHeader;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VcfService {

    private static final String VCF_DIRECTORY = "uploads/vcf";

    @Autowired
    private IndividualRepository individualRepository;

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private VcfFileRepository vcfFileRepository;

    @Autowired
    private VariantRepository variantRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // =========================
    // ① Auto-import at startup
    // =========================
    @PostConstruct
    public void importExistingVcfFiles() {
        System.out.println("🔍 VCF Auto-Import Service is watching: " + new File(VCF_DIRECTORY).getAbsolutePath());

        File folder = new File(VCF_DIRECTORY);
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("❌ VCF upload folder does not exist: " + folder.getAbsolutePath());
            return;
        }

        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.getName().toLowerCase().endsWith(".vcf")) {
                try {
                    saveAndParseVcf(file);
                } catch (Exception e) {
                    System.err.println("❌ Failed to import VCF file: " + file.getName());
                    e.printStackTrace();
                }
            }
        }
    }

    // =============================================
    // ② VCF Parsing from File (used by auto-import)
    // =============================================
    public void saveAndParseVcf(File vcfFileOnDisk) {
        System.out.println("📥 Importing: " + vcfFileOnDisk.getName());

        String filename = vcfFileOnDisk.getName();
        String idPart = filename.split("\\.")[0]; // e.g. "4" from "4.vcf"

        Long individualId;
        try {
            individualId = Long.parseLong(idPart);
        } catch (NumberFormatException e) {
            throw new RuntimeException("⚠️ Cannot determine individual ID from filename: " + filename);
        }

        Individual individual = individualRepository.findById(individualId)
                .orElseThrow(() -> new RuntimeException("⚠️ No individual found for ID: " + individualId));

        // 🧬 Create Sample
        Sample sample = new Sample();
        sample.setSampleLabel("Sample_" + individualId + "_" + System.currentTimeMillis());
        sample.setTissueType("Unknown");
        sample.setIndividual(individual);
        sampleRepository.save(sample);

        // 🧾 Create VcfFile record
        VcfFile vcf = new VcfFile();
        vcf.setFilename(filename);
        vcf.setReferenceGenome("hg19"); // Or extract from header
        vcf.setUploadDate(LocalDateTime.now());
        vcf.setSample(sample);
        vcfFileRepository.save(vcf);

        // 🧬 Parse and save Variants
        List<Variant> variants = new ArrayList<>();

        try (VCFFileReader reader = new VCFFileReader(vcfFileOnDisk, false)) {
            VCFHeader header = reader.getFileHeader();
            for (VariantContext ctx : reader) {
                Variant var = new Variant();
                var.setChrom(ctx.getContig());
                var.setPos(ctx.getStart());
                var.setRef(ctx.getReference().getBaseString());
                var.setAlt(ctx.getAlternateAlleles().toString());
                var.setQual(ctx.hasLog10PError() ? String.valueOf(ctx.getPhredScaledQual()) : "NA");
                var.setFilter(ctx.isFiltered() ? String.join(",", ctx.getFilters()) : "PASS");

                try {
                    String jsonInfo = objectMapper.writeValueAsString(ctx.getAttributes());
                    var.setInfo(jsonInfo);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("❌ Failed to convert INFO to JSON", e);
                }

                var.setVcfFile(vcf);
                variants.add(var);
            }
        }

        variantRepository.saveAll(variants);

        System.out.println("✅ Imported " + variants.size() + " variants from " + filename);
    }

    // ======================================================
    // ③ Support REST upload (e.g. Swagger, Postman, Frontend)
    // ======================================================
    public void saveAndParseVcf(MultipartFile multipartFile) {
        try {
            File tempFile = File.createTempFile("upload_", ".vcf");
            multipartFile.transferTo(tempFile);
            saveAndParseVcf(tempFile); // call the file-based method
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to handle uploaded VCF", e);
        }
    }
}
