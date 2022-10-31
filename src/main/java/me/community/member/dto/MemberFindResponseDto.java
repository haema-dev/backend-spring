package me.community.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
