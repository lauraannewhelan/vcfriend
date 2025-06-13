package com.vcfriend.backend.service;

import com.vcfriend.backend.model.GenomicVariant;
import com.vcfriend.backend.repository.GenomicVariantRepository;
import com.vcfriend.backend.repository.IndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;

@Service
public class VcfParserService {

    @Autowired
    private GenomicVariantRepository genomicVariantRepository;

    @Autowired
    private IndividualRepository individualRepository;

    private static final String GENOME_BUILD = "GRCh38";

    public void parseAndStoreVcf(String filePath) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            int count = 0, skipped = 0;

            // 🔹 Extract individualId from filename (e.g., "7.vcf" → 7)
            String filename = Paths.get(filePath).getFileName().toString();
            String base = filename.contains(".") ? filename.substring(0, filename.lastIndexOf('.')) : filename;
            Integer individualId = Integer.parseInt(base);

            // 🔸 Validate that the individual exists
            if (!individualRepository.existsById(Long.valueOf(individualId))) {
                System.err.println("❌ Individual ID " + individualId + " not found. Aborting parse for file: " + filePath);
                return;
            }

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) continue;

                String[] fields = line.split("\t");
                if (fields.length < 5) continue;

                String chrom = fields[0];
                int pos = Integer.parseInt(fields[1]);
                String ref = fields[3];
                String alt = fields[4];

                String fullReferenceName = GENOME_BUILD + "-" + chrom + "-" + pos + "-" + ref + "-" + alt;

                // ✅ Skip only if this individual already has this variant
                if (genomicVariantRepository.existsByVariantInternalIdAndIndividualId(fullReferenceName, individualId)) {
                    skipped++;
                    continue;
                }

                GenomicVariant variant = new GenomicVariant();
                variant.setReferenceName(fullReferenceName);
                variant.setStartPos(pos);
                variant.setEndPos(pos + ref.length());
                variant.setReferenceBases(ref);
                variant.setAlternateBases(alt);
                variant.setVariantType(ref.length() == alt.length() ? "SNP" : "INDEL");
                variant.setVariantInternalId(fullReferenceName);
                variant.setIndividualId(individualId);

                genomicVariantRepository.save(variant);
                count++;
            }

            System.out.println("✅ Parsed and saved " + count + " variants from: " + filePath);
            if (skipped > 0) {
                System.out.println("⚠️ Skipped " + skipped + " duplicate variants for individual ID " + individualId);
            }

        } catch (IOException | NumberFormatException e) {
            System.err.println("❌ Error processing VCF file: " + filePath);
            e.printStackTrace();
        }
    }
}
