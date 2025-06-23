package com.vcfriend.backend.service;

import com.vcfriend.backend.model.GenomicVariant;
import com.vcfriend.backend.repository.GenomicVariantRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenomicVariantService {

    private final GenomicVariantRepository repository;

    // ✅ Manual constructor to satisfy Spring since Lombok isn't working
    public GenomicVariantService(GenomicVariantRepository repository) {
        this.repository = repository;
    }

    public List<GenomicVariant> getAllVariants() {
        return repository.findAll();
    }

    public List<GenomicVariant> getVariantsByGene(String gene) {
        return repository.findByGeneRefgeneContainingIgnoreCase(gene);
    }

    public List<GenomicVariant> getVariantsByFunc(String func) {
        return repository.findByFuncRefgeneContainingIgnoreCase(func);
    }

    public List<GenomicVariant> getVariantsByIndividualId(Long individualId) {
        System.out.println("🔍 Fetching variants for individual ID: " + individualId);
        return repository.findByIndividualId(individualId);
    }

    public List<GenomicVariant> getVariantsByVariantString(String variant) {
        return repository.findByVariantContainingIgnoreCase(variant);
    }

    public void processAnnotatedCsv(MultipartFile file) throws IOException {
        List<GenomicVariant> variants = new ArrayList<>();
        String fileName = file.getOriginalFilename();
        Long participantId = Long.parseLong(fileName.replace(".csv", ""));

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            for (CSVRecord record : csvParser) {
                GenomicVariant variant = new GenomicVariant();
                variant.setIndividualId(participantId);
                variant.setChr(safeGet(record, "Chr"));
                variant.setStart(safeGet(record, "Start"));
                variant.setEnd(safeGet(record, "End"));
                variant.setRef(safeGet(record, "Ref"));
                variant.setAlt(safeGet(record, "Alt"));
                variant.setFuncRefgene(safeGet(record, "Func.refGene"));
                variant.setGeneRefgene(safeGet(record, "Gene.refGene"));
                variant.setGenedetailRefgene(safeGet(record, "GeneDetail.refGene"));
                variant.setExonicfuncRefgene(safeGet(record, "ExonicFunc.refGene"));
                variant.setAachangeRefgene(safeGet(record, "AAChange.refGene"));
                variant.setRevel(safeGet(record, "REVEL"));
                variant.setOmim(safeGet(record, "OMIM"));
                variant.setGt(safeGet(record, "GT"));
                variant.setBa1(safeGet(record, "BA1"));
                variant.setVariant(safeGet(record, "variant"));
                variant.setInheritance(safeGet(record, "Inheritance"));
                variant.setGnomad40GenomeAf(safeGet(record, "gnomad40_genome_AF"));

                variants.add(variant);
            }
        }

        repository.saveAll(variants);
    }

    private String safeGet(CSVRecord record, String key) {
        return record.isMapped(key) ? record.get(key)
                : record.isMapped("\uFEFF" + key) ? record.get("\uFEFF" + key)
                : null;
    }
}
