package me.community.board.api;

import lombok.RequiredArgsConstructor;
import me.community.board.dto.BoardCreateResponseDto;
import me.community.board.dto.BoardRequestDto;
import me.community.board.dto.BoardResponseDto;
import me.community.board.service.BoardService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<BoardCreateResponseDto> createBoard(@Valid @RequestBody BoardRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(boardService.create(dto));
    }

    // update : 게시글 수정
    @PatchMapping
    public ResponseEntity<BoardResponseDto> updateBoard(@Valid @RequestBody BoardRequestDto dto) {
        boardService.update(dto.getBoard_id(), dto.getEmail(), dto);
        return ResponseEntity.ok().build();
    }

    // delete : 게시글 삭제
    @DeleteMapping
    public ResponseEntity<Void> deleteBoard(@Valid @RequestBody BoardRequestDto dto) {
        boardService.delete(dto.getBoard_id(), dto.getEmail());
        return ResponseEntity.noContent().build();
    }

    // read all : 게시글 전체보기
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getAllBoard(@PageableDefault(sort = "id",
                                                                direction = Sort.Direction.DESC)
                                                                            Pageable pageable) {
        return ResponseEntity.ok().body(boardService.readAll(pageable));
    }
}
