package com.financetracker.controller;

import com.financetracker.model.Account;
import com.financetracker.service.FinanceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin
public class AccountController {

    private final FinanceService financeService;

    public AccountController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody Account a) {
        return ResponseEntity.ok(financeService.addAccount(a));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> list(@PathVariable Long userId) {
        return ResponseEntity.ok(financeService.listAccounts(userId));
    }
}
