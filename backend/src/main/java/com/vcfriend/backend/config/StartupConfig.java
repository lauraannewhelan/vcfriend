package com.vcfriend.backend.config;

import com.vcfriend.backend.model.User;
import com.vcfriend.backend.repository.UserRepository;
import com.vcfriend.backend.service.VcfParserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.nio.file.*;

@Configuration
public class StartupConfig {

    private static final String VCF_DIRECTORY = "/Users/laurawhelan/IdeaProjects/vcfriend/vcf_storage";

    @Bean
    public CommandLineRunner initTestUserAndVariants(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            VcfParserService vcfParserService) {

        return args -> {
            // ✅ Create test user if not exists
            if (userRepository.findByUsername("testuser").isEmpty()) {
                User user = new User();
                user.setUsername("testuser");
                user.setPassword(passwordEncoder.encode("password123"));
                userRepository.save(user);
                System.out.println("✅ Test user 'testuser' created with password 'password123'");
            } else {
                System.out.println("ℹ️ Test user already exists.");
            }

            // ✅ Parse all annotated CSV files in the directory
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(VCF_DIRECTORY), "*.csv")) {
                for (Path csvPath : stream) {
                    System.out.println("🔍 Parsing annotated VCF CSV file: " + csvPath);
                    vcfParserService.parseAndStoreCsv(csvPath.toString());
                }
            } catch (IOException e) {
                System.err.println("❌ Failed to read CSV directory: " + VCF_DIRECTORY);
                e.printStackTrace();
            }
        };
    }
}
