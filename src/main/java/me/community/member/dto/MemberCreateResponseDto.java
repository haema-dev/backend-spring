package me.community.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MemberCreateResponseDto {
    private String email;
    private boolean success;

    @Builder
    public MemberCreateResponseDto(String email, boolean success) {
        this.email = email;
        this.success = success;
    }
}
