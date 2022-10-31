package me.community.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import me.community.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
