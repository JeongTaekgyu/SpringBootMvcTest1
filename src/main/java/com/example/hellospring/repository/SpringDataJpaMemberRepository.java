package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
        // 스프링 데이터 JPA가 JpaRepository 를 상속받고 있으면
        // SpringDataJpaMemberRepository 를 스프링 빈으로 자동 등록해준다 (구현체로 만들어서 등록을 해준다.)
    // 이것을 SpringConfig에서 가져다 쓰면 된다.

    @Override
    Optional<Member> findByName(String name);
    // JPQL에서 다음과 같이 쿼리를 작성한다. select m from Member m where m.name = ?
}
