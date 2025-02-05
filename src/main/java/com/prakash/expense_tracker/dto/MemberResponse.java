package com.prakash.expense_tracker.dto;

import java.util.List;

public record MemberResponse(
        Long id,
        String name,
        List<PaymentResponse> payments
) {}