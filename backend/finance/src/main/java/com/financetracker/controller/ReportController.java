package com.financetracker.controller;

import com.financetracker.service.FinanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class ReportController {
    private final FinanceService financeService;

    public ReportController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping("/monthly")
    public ResponseEntity<?> monthly(@RequestParam Long userId, @RequestParam int year, @RequestParam int month) {
        return ResponseEntity.ok(financeService.monthlyReport(userId, year, month));
    }
}
