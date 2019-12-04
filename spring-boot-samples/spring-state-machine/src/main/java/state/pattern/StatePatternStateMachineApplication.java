package state.pattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import state.pattern.enums.ApproveEvents;
import state.pattern.service.StatePatternStateMachine;

@Slf4j
@SpringBootApplication(scanBasePackageClasses = {StatePatternStateMachine.class})
public class StatePatternStateMachineApplication implements CommandLineRunner, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        StatePatternStateMachine stateMachine = applicationContext.getBean(StatePatternStateMachine.class);
        log.info("你发起申请涨工资...");
        log.info("事件是否被状态机处理 result: {}", stateMachine.sendEvent(ApproveEvents.APPLY, "发起申请涨工资"));
        log.info("你觉得不妥，想改为申请加班...");
        log.info("事件是否被状态机处理 result: {}", stateMachine.sendEvent(ApproveEvents.APPLY, "发起申请加班"));
        log.info("老板答复了你的申请...");
        log.info("事件是否被状态机处理 result: {}", stateMachine.sendEvent(ApproveEvents.APPROVER_REPULSE, "代码不优雅"));
        log.info("你赶紧改为申请加班...");
        log.info("事件是否被状态机处理 result: {}", stateMachine.sendEvent(ApproveEvents.APPLY, "发起申请加班"));
        log.info("老板答复了你的申请...");
        log.info("事件是否被状态机处理 result: {}", stateMachine.sendEvent(ApproveEvents.APPROVER_APPROVAL, "辛苦"));
    }


    public static void main(String[] args) {
        SpringApplication.run(StatePatternStateMachineApplication.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}