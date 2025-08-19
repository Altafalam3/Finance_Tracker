package com.financetracker.controller;

import com.financetracker.dto.LoginRequest;
import com.financetracker.dto.RegisterRequest;
import com.financetracker.model.User;
import com.financetracker.service.FinanceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {

    private final FinanceService financeService;

    public AuthController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
        User u = new User();
        u.setName(req.name());
        u.setEmail(req.email());
        u.setPassword(req.password());
        return ResponseEntity.ok(financeService.register(u));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        User u = financeService.login(req.email(), req.password());
        if (u == null) return ResponseEntity.status(401).body("Invalid credentials");
        return ResponseEntity.ok(u);
    }
}
