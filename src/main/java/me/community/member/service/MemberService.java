package me.community.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.community.member.dto.MemberFindResponseDto;
import me.community.member.dto.MemberRequestDto;
import me.community.member.dto.MemberCreateResponseDto;
import me.community.member.entity.Member;
import me.community.member.repository.MemberQueryDslRepository;
import me.community.member.repository.MemberRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryDslRepository memberQueryDslRepository;

    @Transactional(readOnly = true)
    public MemberFindResponseDto find(final MemberRequestDto requestDto) {

        Member findMember = memberQueryDslRepository.findMember(requestDto);

        MemberFindResponseDto dto = MemberFindResponseDto.builder()
                                                            .email(findMember.getEmail())
                                                            .name(findMember.getName()).build();
        return dto;
    }

    @Transactional
    public MemberCreateResponseDto create(final MemberRequestDto requestDto) {

        Member member = memberRepository.save(requestDto.toEntity());
        MemberCreateResponseDto dto = MemberCreateResponseDto.builder()
                                                    .email(member.getEmail())
                                                    .success(true).build();
        return dto;
    }

    @Transactional
    public void update(final String email, final MemberRequestDto dto) {
        Member member = memberRepository.findByEmail(email);
        member.memberChange(dto.getName(), dto.getPassword());
    }

    @Transactional
    public void delete(final String email) {
        memberRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<MemberFindResponseDto> readAll(Pageable pageable) {
        return memberQueryDslRepository.findAllMembers(pageable);
    }
}
