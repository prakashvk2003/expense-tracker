package com.prakash.expense_tracker.service;

import com.prakash.expense_tracker.dto.*;
import com.prakash.expense_tracker.model.*;
import com.prakash.expense_tracker.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepo;
    private final MemberRepository memberRepo;
    private final PaymentRepository paymentRepo;

    @Transactional
    public ExpenseResponse createExpense(ExpenseRequest request) {
        Expense expense = new Expense();
        expense.setDescription(request.description());
        expense.setAmount(request.amount());
        expense.setDate(LocalDate.now());
        Expense savedExpense = expenseRepo.save(expense);

        double share = request.amount() / request.memberIds().size();

        List<PaymentResponse> payments = request.memberIds().stream()
                .map(memberId -> {
                    Member member = memberRepo.findById(memberId)
                            .orElseThrow(() -> new RuntimeException("Member not found"));

                    Payment payment = new Payment();
                    payment.setAmount(share);
                    payment.setExpense(savedExpense);
                    payment.setMember(member);
                    paymentRepo.save(payment);

                    return new PaymentResponse(
                            payment.getId(),
                            payment.getAmount(),
                            member.getId(),
                            member.getName()
                    );
                }).toList();

        return new ExpenseResponse(
                savedExpense.getId(),
                savedExpense.getDescription(),
                savedExpense.getAmount(),
                savedExpense.getDate(),
                payments
        );
    }

    public List<ExpenseResponse> getAllExpenses() {
        return expenseRepo.findAll().stream()
                .map(expense -> new ExpenseResponse(
                        expense.getId(),
                        expense.getDescription(),
                        expense.getAmount(),
                        expense.getDate(),
                        expense.getPayments().stream()
                                .map(p -> new PaymentResponse(
                                        p.getId(),
                                        p.getAmount(),
                                        p.getMember().getId(),
                                        p.getMember().getName()
                                )).toList()
                )).toList();
    }
}