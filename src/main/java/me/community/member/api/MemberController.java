package me.community.member.api;

import lombok.RequiredArgsConstructor;
import me.community.member.dto.MemberFindResponseDto;
import me.community.member.dto.MemberRequestDto;
import me.community.member.dto.MemberCreateResponseDto;
import me.community.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping()
    public ResponseEntity<MemberFindResponseDto> getMember(@Valid @RequestBody MemberRequestDto dto) {
        return ResponseEntity.ok().body(memberService.find(dto));
    }

    @PostMapping
    public ResponseEntity<MemberCreateResponseDto> createMember(@Valid @RequestBody MemberRequestDto dto) {
        return ResponseEntity.ok().body(memberService.create(dto));
    }

}
