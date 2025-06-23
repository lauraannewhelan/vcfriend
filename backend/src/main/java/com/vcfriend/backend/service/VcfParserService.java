package com.vcfriend.backend.service;

import com.vcfriend.backend.model.GenomicVariant;
import com.vcfriend.backend.repository.GenomicVariantRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;

@Service
public class VcfParserService {

    private final GenomicVariantRepository repository;

    // ✅ Manual constructor (since Lombok is not working)
    public VcfParserService(GenomicVariantRepository repository) {
        this.repository = repository;
    }

    public void parseAndStoreCsv(String filePath) {
        List<GenomicVariant> variants = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        String fileName = Paths.get(filePath).getFileName().toString();
        int individualId = Integer.parseInt(fileName.replace(".csv", ""));

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            for (CSVRecord record : csvParser) {
                String chr = safeGet(record, "Chr");
                String start = safeGet(record, "Start");
                String end = safeGet(record, "End");
                String ref = safeGet(record, "Ref");
                String alt = safeGet(record, "Alt");
                String variantString = safeGet(record, "variant");

                String key = individualId + "|" + chr + "|" + start + "|" + end + "|" + ref + "|" + alt + "|" + variantString;
                if (seen.contains(key)) continue;
                seen.add(key);

                GenomicVariant variant = new GenomicVariant();
                variant.setIndividualId((long) individualId);
                variant.setChr(chr);
                variant.setStart(start);
                variant.setEnd(end);
                variant.setRef(ref);
                variant.setAlt(alt);
                variant.setFuncRefgene(safeGet(record, "Func.refGene"));
                variant.setGeneRefgene(safeGet(record, "Gene.refGene"));
                variant.setGenedetailRefgene(safeGet(record, "GeneDetail.refGene"));
                variant.setExonicfuncRefgene(safeGet(record, "ExonicFunc.refGene"));
                variant.setAachangeRefgene(safeGet(record, "AAChange.refGene"));
                variant.setRevel(safeGet(record, "REVEL"));
                variant.setOmim(safeGet(record, "OMIM"));
                variant.setGt(safeGet(record, "GT"));
                variant.setBa1(safeGet(record, "BA1"));
                variant.setVariant(variantString);
                variant.setInheritance(safeGet(record, "Inheritance"));
                variant.setGnomad40GenomeAf(safeGet(record, "gnomad40_genome_AF"));

                variants.add(variant);
            }

            repository.saveAll(variants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String safeGet(CSVRecord record, String key) {
        return record.isMapped(key) ? record.get(key)
                : record.isMapped("\uFEFF" + key) ? record.get("\uFEFF" + key)
                : null;
    }
}
