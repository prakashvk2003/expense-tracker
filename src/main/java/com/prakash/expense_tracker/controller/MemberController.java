package com.prakash.expense_tracker.controller;

import com.prakash.expense_tracker.dto.MemberRequest;
import com.prakash.expense_tracker.dto.MemberResponse;
import com.prakash.expense_tracker.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse createMember(@RequestBody MemberRequest request) {
        return memberService.createMember(request);
    }

    @GetMapping
    public List<MemberResponse> getAllMembers() {
        return memberService.getAllMembers();
    }
}