package com.vcfriend.backend.config;

import com.vcfriend.backend.model.User;
import com.vcfriend.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class StartupConfig {

    @Bean
    public CommandLineRunner initTestUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("testuser").isEmpty()) {
                User user = new User();
                user.setUsername("testuser");
                user.setPassword(passwordEncoder.encode("password123")); // 🔐 store as hash

                userRepository.save(user);
                System.out.println("✅ Test user 'testuser' created with password 'password123'");
            } else {
                System.out.println("ℹ️ Test user already exists.");
            }
        };
    }
}
