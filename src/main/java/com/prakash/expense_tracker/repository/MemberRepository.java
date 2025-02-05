package com.prakash.expense_tracker.repository;

import com.prakash.expense_tracker.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}