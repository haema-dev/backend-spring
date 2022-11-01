package me.community.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.community.board.entity.Board;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @Builder
    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public void memberChange(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
