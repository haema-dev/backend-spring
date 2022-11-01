package me.community.board.entity;

import lombok.*;
import me.community.member.entity.Member;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Getter
@NoArgsConstructor
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @ManyToOne
    @JoinColumn(name = "email")
    private Member member;

    @Builder
    public Board(Long id, String title, String content, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void boardChange(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
