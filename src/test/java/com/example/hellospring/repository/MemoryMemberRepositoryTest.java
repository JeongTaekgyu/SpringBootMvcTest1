package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    //MemberRepository repository = new MemoryMemberRepository();

    // ★★ Test 끝날때 마다 Repository를 지워준다 ★★
    // 이유는 똑같은 데이터 들어가면 에러가 날 수 있음
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    // Test는 순서가 보장이 안된다.

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // Optional에서 값을 꺼낼때는 get()으로 꺼냄 ( findById는 반환값이 Optional 이다 )

        //System.out.println("result = " + (result == member));
        // 위에 행을 아래 처럼 사용한다.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
