package com.financetracker.controller;

import com.financetracker.dto.TransactionRequest;
import com.financetracker.model.Transaction;
import com.financetracker.service.FinanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin
public class TransactionController {

    private final FinanceService financeService;

    public TransactionController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody TransactionRequest req) {
        Transaction t = new Transaction();
        t.setUserId(req.userId());
        t.setAccountId(req.accountId());
        t.setAmount(req.amount());
        t.setIncome(req.income());
        t.setCategory(req.category());
        t.setTags(req.tags());
        t.setDescription(req.description());
        return ResponseEntity.ok(financeService.addTransaction(t));
    }

    @GetMapping("/recent/{userId}")
    public ResponseEntity<?> recent(@PathVariable Long userId) {
        return ResponseEntity.ok(financeService.recent(userId));
    }
}
