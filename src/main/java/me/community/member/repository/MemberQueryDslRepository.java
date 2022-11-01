package me.community.member.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.community.member.dto.MemberFindResponseDto;
import me.community.member.dto.MemberRequestDto;
import me.community.member.entity.Member;
import me.community.member.entity.QMember;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class MemberQueryDslRepository {

    private final JPAQueryFactory query;

    public List<MemberFindResponseDto> findAllMembers(Pageable pageable) {
        QMember m = QMember.member;
        return query.select(Projections.constructor(MemberFindResponseDto.class, m.email, m.name))
                    .from(m).limit(pageable.getPageSize()).fetch();
    }

    public Member findMember(MemberRequestDto requestDto){
        QMember m = QMember.member;
        return query.select(m).from(m).where(eqEmailType(requestDto.getEmail(), m)).fetchOne();
    }

    private Predicate eqEmailType(String email, QMember member) {
        if (email == null) {
            return null;
        }
        return member.email.eq(email);
    }

    private Predicate eqNameType(String name, QMember member) {
        if (name == null) {
            return null;
        }
        return member.name.eq(name);
    }

    private Predicate eqPasswordType(String password, QMember member) {
        if (password == null) {
            return null;
        }
        return member.password.eq(password);
    }
}
