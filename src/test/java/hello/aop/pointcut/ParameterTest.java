package hello.aop.pointcut;


import hello.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(ParameterTest.ParameterAspect.class)
public class ParameterTest{

    @Autowired
    MemberService memberService;

    @Test
    void success() {
       log.info("memberService Proxy={}", memberService.getClass());
       memberService.hello("helloA");
    }

    @Aspect
    @Slf4j
    static class ParameterAspect {
        @Pointcut("execution(* hello.aop.member..*.*(..))")
        private void allmember() {}

        @Around("allmember()")
        public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
            Object first_Param = joinPoint.getArgs()[0];
            log.info("first_Param={}", first_Param);
            return joinPoint.proceed();
        }
    }
}
