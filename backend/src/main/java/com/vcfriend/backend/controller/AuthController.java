package com.vcfriend.backend.controller;

import com.vcfriend.backend.model.User;
import com.vcfriend.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public boolean login(@RequestBody LoginRequest loginRequest) {
        System.out.println("🔐 Attempt login for: " + loginRequest.username());

        Optional<User> userOpt = userRepository.findByUsername(loginRequest.username());

        if (userOpt.isEmpty()) {
            System.out.println("❌ User not found");
            return false;
        }

        User user = userOpt.get();
        System.out.println("✅ Found user, checking password...");

        boolean match = user.getPassword().equals(loginRequest.password());

        System.out.println("🔎 Password match: " + match);

        return match;
    }

    public record LoginRequest(String username, String password) {}
}
