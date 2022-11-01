package me.community.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.community.member.entity.Member;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {
    private String email;
    private String password;
    private String name;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .name(name).build();
    }
}
