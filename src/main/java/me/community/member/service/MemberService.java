package me.community.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.community.member.dto.MemberFindResponseDto;
import me.community.member.dto.MemberRequestDto;
import me.community.member.dto.MemberCreateResponseDto;
import me.community.member.entity.Member;
import me.community.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberFindResponseDto find(final MemberRequestDto requestDto) {

        Member member = memberRepository.findByEmail(requestDto.getEmail());
        MemberFindResponseDto dto = MemberFindResponseDto.builder()
                                                            .email(member.getEmail())
                                                            .name(member.getName()).build();
        return dto;
    }

    public MemberCreateResponseDto create(final MemberRequestDto requestDto) {

        Member member = memberRepository.save(requestDto.toEntity());
        MemberCreateResponseDto dto = MemberCreateResponseDto.builder()
                                                    .email(member.getEmail())
                                                    .success(true).build();
        return dto;
    }
}
