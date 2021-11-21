package com.example.hellospring.controller;

import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    // @Autowired하면 Dependency Injection 된다.
    // 스프링 컨테이너에서 memberService를 가져옴
    @Autowired  // memberService를 스프링이 스프링 컨테이너에 있는 memberService를 가져다 연결을 시켜준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
