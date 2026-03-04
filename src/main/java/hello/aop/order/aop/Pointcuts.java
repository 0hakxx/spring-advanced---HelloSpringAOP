package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(* hello.aop.order..*(..))") // hello.aop.order 패키지와 하위 패키지의 모든 메서드
    public void allOrder() {} // pointcut signature

    // 클래스 이름 패턴이 *Service 인 것
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {} // pointcut signature

    @Pointcut("allService() && allOrder()") // 클래스 이름 패턴이 *Service 인 것
    public void orderAndService() {}
}
