package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; // jpa는 EntityManager 라는 것으로 동작을 한다.
    // 스프링부트가 EntityManager 라는걸 자동으로 생성해준다 (당연히 jpa 라이브러리가 있어야함)

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // jpa가 insert쿼리 만들어서 db에 넣고 id까지 member에다 setId 해준다
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);  // 조회하기
        return Optional.ofNullable(member); // 값이 null 인 경우 Optional을 반환한다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // entity(객체)를 대상으로 쿼리를 날린다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();   // m(객체(entity) 자체를 select 한다)
    }
}
