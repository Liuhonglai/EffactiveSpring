package spring.state.machine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import spring.state.machine.enums.ApproveEvents;
import spring.state.machine.enums.ApproveStatus;

import javax.annotation.Resource;

@Slf4j
@SpringBootApplication(scanBasePackageClasses = {SpringStateMachineConfig.class})
public class SpringStateMachineApplication implements CommandLineRunner {

    @Resource
    private StateMachine<ApproveStatus, ApproveEvents> stateMachine;

    @Override
    public void run(String... args) throws Exception {
        log.info("你发起申请涨工资...");
        log.info("事件是否被状态机处理 result: {}", StateMachineTemplate.invokeStateMachine("发起申请涨工资", ApproveEvents.APPLY, stateMachine));
        log.info("你觉得不妥，想改为申请加班...");
        log.info("事件是否被状态机处理 result: {}", StateMachineTemplate.invokeStateMachine("发起申请加班", ApproveEvents.APPLY, stateMachine));
        log.info("老板答复了你的申请...");
        log.info("事件是否被状态机处理 result: {}", StateMachineTemplate.invokeStateMachine("代码不优雅", ApproveEvents.APPROVER_REPULSE, stateMachine));
        log.info("你赶紧改为申请加班...");
        log.info("事件是否被状态机处理 result: {}", StateMachineTemplate.invokeStateMachine("发起申请加班", ApproveEvents.APPLY, stateMachine));
        log.info("老板答复了你的申请...");
        log.info("事件是否被状态机处理 result: {}", StateMachineTemplate.invokeStateMachine("辛苦", ApproveEvents.APPROVER_APPROVAL, stateMachine));
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringStateMachineApplication.class, args);
    }
}