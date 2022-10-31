package me.community.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.community.board.entity.Board;
import me.community.member.entity.Member;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {
    private Long board_id;
    private String email;
    private String title;
    private String content;

    public Board toEntity(){
        return Board.builder()
                .id(board_id)
                .member(Member.builder().email(email).build())
                .title(title)
                .content(content).build();
    }
}
