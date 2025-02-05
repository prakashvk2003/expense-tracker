package com.prakash.expense_tracker.dto;

import java.util.List;

public record ExpenseRequest(
        String description,
        Double amount,
        List<Long> memberIds
) {}