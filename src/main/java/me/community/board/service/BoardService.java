package me.community.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.community.board.dto.BoardCreateResponseDto;
import me.community.board.dto.BoardRequestDto;
import me.community.board.dto.BoardResponseDto;
import me.community.board.entity.Board;
import me.community.board.repository.BoardQueryDslRepository;
import me.community.board.repository.BoardRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardQueryDslRepository boardQueryDslRepository;

    @Transactional(readOnly = true)
    public BoardResponseDto read(final Long board_id) {

        Board findBoard = boardQueryDslRepository.findBoard(board_id);

        BoardResponseDto dto = BoardResponseDto.builder()
                                                .board_id(findBoard.getId())
                                                .title(findBoard.getTitle())
                                                .content(findBoard.getContent())
                                                .name(findBoard.getMember().getName()).build();
        return dto;
    }

    @Transactional
    public BoardCreateResponseDto create(final BoardRequestDto requestDto) {
        Board board = boardRepository.save(requestDto.toEntity());

        BoardCreateResponseDto dto = BoardCreateResponseDto.builder()
                .board_id(board.getId())
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

    @Transactional
    public void delete(final Long board_id, final String email) {
        Board board = boardRepository.findById(board_id).orElseThrow();
        if(byWrittenEmail(board, email)){
            boardRepository.delete(board);
        }
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> readAll(Pageable pageable) {
        return boardQueryDslRepository.findAllBoards(pageable);
    }

    private boolean byWrittenEmail(Board board, String email) {
        if(board.getMember().getEmail().equals(email)) {
            return true;
        }
        return false;
    }
}
