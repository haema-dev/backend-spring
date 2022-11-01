package me.community.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import me.community.board.entity.Board;
import org.springframework.stereotype.Repository;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
