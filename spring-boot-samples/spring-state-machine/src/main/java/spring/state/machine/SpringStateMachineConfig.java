package spring.state.machine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import spring.state.machine.actions.ApplyAction;
import spring.state.machine.actions.ApproveAction;
import spring.state.machine.actions.RepulseAction;
import spring.state.machine.enums.ApproveEvents;
import spring.state.machine.enums.ApproveStatus;

import javax.annotation.Resource;
import java.util.EnumSet;

import static spring.state.machine.enums.ApproveStatus.*;
import static spring.state.machine.enums.ApproveStatus.APPROVED;

@Slf4j
@Configuration
@EnableStateMachine
public class SpringStateMachineConfig {

    @Resource
    private ApplyAction applyAction;
    @Resource
    private ApproveAction approveAction;
    @Resource
    private RepulseAction repulseAction;

    @Bean
    @Scope(scopeName="prototype")
    public StateMachine<ApproveStatus, ApproveEvents> stateMachine() throws Exception {
        StateMachineBuilder.Builder<ApproveStatus, ApproveEvents> builder = StateMachineBuilder.builder();

        builder.configureConfiguration()
                .withConfiguration()
                .listener(listener())
                .autoStartup(true);

        builder.configureStates()
                .withStates()
                .initial(INIT)
                .end(APPROVED)
                .states(EnumSet.allOf(ApproveStatus.class));

        builder.configureTransitions()
                .withExternal().source(INIT).target(TO_BE_APPROVED).event(ApproveEvents.APPLY).action(applyAction).and()
                .withExternal().source(TO_BE_APPROVED).target(APPROVED).event(ApproveEvents.APPROVER_APPROVAL).action(approveAction).and()
                .withExternal().source(TO_BE_APPROVED).target(REPULSE).event(ApproveEvents.APPROVER_REPULSE).action(repulseAction).and()
                .withExternal().source(REPULSE).target(TO_BE_APPROVED).event(ApproveEvents.APPLY).action(applyAction);

        return builder.build();
    }

    @Bean
    public StateMachineListener<ApproveStatus, ApproveEvents> listener() {
        return new StateMachineListenerAdapter<ApproveStatus, ApproveEvents>() {
            @Override
            public void stateChanged(State<ApproveStatus, ApproveEvents> from, State<ApproveStatus, ApproveEvents> to) {
                log.info("State change to " + to.getId());
            }
        };
    }
}