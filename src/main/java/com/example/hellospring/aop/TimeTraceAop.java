package com.example.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // aop는 이걸 사용
@Component
public class TimeTraceAop {

    // aop는 중간에 인터셉터해서 호출 할 수 있다.
    @Around("execution(* com.example.hellospring..*(..))")  // 해당 경로 하위에 있는
    public Object execut(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try{
            //Object result = joinPoint.proceed();    // 다음 메서드로 진행이 됨
            //return result;
            return joinPoint.proceed(); // 위에 두 행을 inline으로 합침
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
