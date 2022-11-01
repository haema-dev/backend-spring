package me.community.member.api;

import lombok.RequiredArgsConstructor;
import me.community.member.dto.MemberFindResponseDto;
import me.community.member.dto.MemberRequestDto;
import me.community.member.dto.MemberCreateResponseDto;
import me.community.member.service.MemberService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    // read : 멤버 상세보기
    @GetMapping("/detail")
    public ResponseEntity<MemberFindResponseDto> getMember(@Valid @RequestBody MemberRequestDto dto) {
        return ResponseEntity.ok().body(memberService.find(dto));
    }

    // create : 멤버 생성
    @PostMapping
    public ResponseEntity<MemberCreateResponseDto> createMember(@Valid @RequestBody MemberRequestDto dto) {
        return ResponseEntity.ok().body(memberService.create(dto));
    }

    // update : 멤버 수정
    @PatchMapping
    public ResponseEntity<MemberFindResponseDto> updateMember(@Valid @RequestBody MemberRequestDto dto) {
        memberService.update(dto.getEmail(), dto);
        return ResponseEntity.ok().build();
    }

    // delete : 멤버 삭제
    @DeleteMapping
    public ResponseEntity<Void> deleteMember(@Valid @RequestBody MemberRequestDto dto) {
        memberService.delete(dto.getEmail());
        return ResponseEntity.noContent().build();
    }

    // read all : 멤버 전체보기
    @GetMapping
    public ResponseEntity<List<MemberFindResponseDto>> getAllMember(@PageableDefault(sort = "id",
                                                                        direction = Sort.Direction.DESC)
                                                                     Pageable pageable) {
        return ResponseEntity.ok().body(memberService.readAll(pageable));
    }

}
