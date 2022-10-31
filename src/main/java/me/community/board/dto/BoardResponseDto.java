package me.community.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long board_id;
    private String title;
    private String content;
    private String email;

    @Builder
    public BoardResponseDto(Long board_id, String title, String content, String email) {
        this.board_id = board_id;
        this.title = title;
        this.content = content;
        this.email = email;
    }
}
