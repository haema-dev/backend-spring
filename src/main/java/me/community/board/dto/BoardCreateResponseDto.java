package me.community.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class BoardCreateResponseDto {
    private Long board_id;
    private String title;
    private String content;
    private String name;

    @Builder
    public BoardCreateResponseDto(Long board_id, String title, String content) {
        this.board_id = board_id;
        this.title = title;
        this.content = content;
    }
}
