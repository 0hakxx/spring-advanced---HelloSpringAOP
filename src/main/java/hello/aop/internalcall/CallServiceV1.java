package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    @Autowired public void setCallServiceV1(CallServiceV1 callServiceV1) {
        this.callServiceV1 = callServiceV1;
    }

    public void external() {

        log.info("call external");
    }
    public void internal() {
        log.info("call internal");
    }
}