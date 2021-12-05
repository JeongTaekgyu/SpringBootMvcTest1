package com.example.hellospring;

import com.example.hellospring.repository.JdbcTemplateMemberRepository;
import com.example.hellospring.repository.JpaMemberRepository;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //@PersistenceContext
    //private EntityManager em;

//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // 스프링 데이터 jpa가 구현체를 만들어 놓은게 등록이됨
    private final MemberRepository memberRepository;

    @Autowired  // 생성자가 하나인경우는 생략해도됨, 그러나 명시하는게 좋을듯
    public SpringConfig(MemberRepository memberRepository) {
        // 스프링 컨네이너에서 MemberRepository를 찾는다 근데 등록해 놓은게 ..SpringDataJpaMemberRepository
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        // memberService에다 의존관계 셋팅을 해줘야함
        return new MemberService(memberRepository);
    }
//    @Bean
//    public MemberRepository memberRepository() {
////      return new MemoryMemberRepository();
////      return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
//        //return new JpaMemberRepository(em);
//    }
}
