package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 아래 @Repository 사용하면 동일한 타입(MemberRepository)의 빈을 2개 등록해서
// 문제가 발생한다.
//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;  // 0, 1, 2, ... key값을 생성

    // MemberRepository 에 있는걸 재정의함
    @Override
    public Member save(Member member) {
        System.out.println("~~~ MemoryMemberRepository save");
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // 결과가 null 일 수도 있기때문에 Optional.ofNullable 로 감싸서 반환할 수 있다.
        // (그럼 클라이언트에서 뭘 할 수 있다.)
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // 루프를 돌면서 map에서 하나 찾으면 걔를 반환한다.
        // 끝까지 돌렸는데 없으면 Optional에 null을 포함해서 반환한다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // list로 반환함 (루프돌리기도 편함)
        // store.values()가 Map<Long, Member> store의 Member이다
    }

    public void clearStore(){
        store.clear();
    }
}
