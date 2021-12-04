package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service    // 서비스가 스프링이 올라올 때 스프링이 스프링 컨테이너의 MemberService를 등록해준다.
// - jpa는 join들어올 때 모든 data변경이 transaction 안에서 실행돼야한다.
@Transactional  // jpa 사용할 때 주의해야 할게 @Transactional 이란게 있어야한다.
// -jPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.
// 스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고, 메서드가 정상 종료되면 트랜잭션을
// 커밋한다. 만약 런타임 예외가 발생하면 롤백한다.
public class MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // 외부에서 memberRepository를 넣어준다. ★★(DI : Dependency Injection)
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    /**
     * 회원가입
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원x (테스트니까 예시를 그냥 이렇게 잡음)
        /*Optional<Member> result = memberRepository.findByName(member.getName());
        // result가 null이 아니라 값이 있으면 {}로직이 동작을 한다. (Optional 이기 때문에 가능)
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
