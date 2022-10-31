package me.community.board.api;

import lombok.RequiredArgsConstructor;
import me.community.board.dto.BoardRequestDto;
import me.community.board.dto.BoardResponseDto;
import me.community.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    // read : 게시글 상세보기
    @GetMapping("/{board_id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long board_id) {
        return ResponseEntity.ok().body(boardService.read(board_id));
    }

    // create : 게시글 작성
    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@Valid @RequestBody BoardRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(boardService.create(dto));
    }

    @PatchMapping
    public ResponseEntity<BoardResponseDto> updateBoard(@Valid @RequestBody BoardRequestDto dto) {
        boardService.update(dto.getBoard_id(), dto.getEmail(), dto);
        return ResponseEntity.ok().build();
    }

}
