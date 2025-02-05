package com.prakash.expense_tracker.controller;

import com.prakash.expense_tracker.dto.ExpenseRequest;
import com.prakash.expense_tracker.dto.ExpenseResponse;
import com.prakash.expense_tracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseResponse createExpense(@RequestBody ExpenseRequest request) {
        return expenseService.createExpense(request);
    }

    @GetMapping
    public List<ExpenseResponse> getAllExpenses() {
        return expenseService.getAllExpenses();
    }
}