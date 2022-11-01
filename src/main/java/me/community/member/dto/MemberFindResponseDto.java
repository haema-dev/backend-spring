package me.community.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.community.member.entity.Member;


@Getter
@NoArgsConstructor
public class MemberFindResponseDto {
    private String email;
    private String name;

    @Builder
    public MemberFindResponseDto(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public static MemberFindResponseDto of(Member member){
        return MemberFindResponseDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .build();
    }
}
