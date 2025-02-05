package com.prakash.expense_tracker.service;

import com.prakash.expense_tracker.dto.*;
import com.prakash.expense_tracker.model.Member;
import com.prakash.expense_tracker.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepo;

    public MemberResponse createMember(MemberRequest request) {
        Member member = new Member();
        member.setName(request.name());
        Member savedMember = memberRepo.save(member);
        return new MemberResponse(
                savedMember.getId(),
                savedMember.getName(),
                List.of()
        );
    }

    public List<MemberResponse> getAllMembers() {
        return memberRepo.findAll().stream()
                .map(member -> new MemberResponse(
                        member.getId(),
                        member.getName(),
                        member.getPayments().stream()
                                .map(p -> new PaymentResponse(
                                        p.getId(),
                                        p.getAmount(),
                                        member.getId(),
                                        member.getName()
                                )).toList()
                )).toList();
    }
}