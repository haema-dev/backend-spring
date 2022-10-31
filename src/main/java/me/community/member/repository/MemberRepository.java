package me.community.member.repository;

import me.community.member.dto.MemberRequestDto;
import me.community.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}
