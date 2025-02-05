package com.prakash.expense_tracker.repository;

import com.prakash.expense_tracker.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}