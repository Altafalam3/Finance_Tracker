package com.financetracker.dto;

import java.math.BigDecimal;

public record TransactionRequest(Long userId, Long accountId, BigDecimal amount, Boolean income, String category, String tags, String description) { }
