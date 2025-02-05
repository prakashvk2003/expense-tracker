package com.prakash.expense_tracker.dto;

import java.time.LocalDate;
import java.util.List;

public record ExpenseResponse(
        Long id,
        String description,
        Double amount,
        LocalDate date,
        List<PaymentResponse> payments
) {}