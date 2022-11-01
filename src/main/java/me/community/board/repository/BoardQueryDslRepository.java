package me.community.board.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.community.board.dto.BoardResponseDto;
import me.community.board.entity.Board;
import me.community.board.entity.QBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class BoardQueryDslRepository {

    private final JPAQueryFactory query;

    public List<BoardResponseDto> findAllBoards(Pageable pageable) {
        QBoard b = QBoard.board;
        return query.select(Projections.constructor(BoardResponseDto.class, b.id, b.title, b.content, b.member.name))
                .from(b).limit(pageable.getPageSize()).fetch();
    }

    public Board findBoard(Long board_id){
        QBoard b = QBoard.board;
        return query.select(b).from(b).where(eqIdType(board_id, b)).fetchOne();
    }

    private Predicate eqIdType(Long id, QBoard board) {
        if (id == null) {
            return null;
        }
        return board.id.eq(id);
    }

    private Predicate eqTitleType(String title, QBoard board) {
        if (title == null) {
            return null;
        }
        return board.title.eq(title);
    }

    private Predicate eqContentType(String content, QBoard board) {
        if (content == null) {
            return null;
        }
        return board.content.eq(content);
    }
}
