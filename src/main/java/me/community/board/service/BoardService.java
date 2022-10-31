package me.community.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.community.board.dto.BoardRequestDto;
import me.community.board.dto.BoardResponseDto;
import me.community.board.entity.Board;
import me.community.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public BoardResponseDto read(final Long board_id) {

        Board board = boardRepository.findById(board_id).orElseThrow();
        BoardResponseDto dto = BoardResponseDto.builder()
                                                .board_id(board.getId())
                                                .title(board.getTitle())
                                                .content(board.getContent()).build();
        return dto;
    }

    @Transactional
    public BoardResponseDto create(final BoardRequestDto requestDto) {
        Board board = boardRepository.save(requestDto.toEntity());

        BoardResponseDto dto = BoardResponseDto.builder()
                .board_id(board.getId())
                .email(board.getMember().getEmail())
                .title(board.getTitle())
                .content(board.getContent()).build();
        return dto;
    }

    @Transactional
    public void update(final Long board_id, final String email, final BoardRequestDto updateRequestDto) {

        Board board = boardRepository.findById(board_id).orElseThrow();

        if(byWrittenEmail(board, email)){
            board.boardChange(updateRequestDto.getTitle(), updateRequestDto.getContent());
        }
    }

    private boolean byWrittenEmail(Board board, String email) {

        if(board.getMember().getEmail().equals(email)) {
            return true;
        }
        return false;
    }
}
