package com.prakash.expense_tracker.dto;

public record PaymentResponse(
        Long id,
        Double amount,
        Long memberId,
        String memberName
) {}