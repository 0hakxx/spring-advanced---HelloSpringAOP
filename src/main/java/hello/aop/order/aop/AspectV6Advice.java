package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {
    @Around("hello.aop.order.aop.Pointcuts.orderAndService()") // 클래스 이름 패턴이 *Service 인 것
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //@Before
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            //@AfterReturning
            Object result = joinPoint.proceed();
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //@AfterThrowing
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
                //@After
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }
    @Before("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("orderAndService 호출 전 로그 출력");
        log.info("[before] {}", joinPoint.getSignature());
    }
    @AfterReturning("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfterReturning(JoinPoint joinPoint) {
        log.info("orderAndService 호출 후 로그 출력");
        log.info("리턴 값 : [after-return] {}", joinPoint.getSignature());
    }
    @AfterThrowing("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfterThrowing(JoinPoint joinPoint) {
        log.info("orderAndService 호출 중 예외 발생 로그 출력");
        log.info("[after-throwing] {}", joinPoint.getSignature());
    }
    @After("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("orderAndService 호출 후 로그 출력 (예외 발생 여부 상관 없음)");
        log.info("[after] {}", joinPoint.getSignature());
    }
}
