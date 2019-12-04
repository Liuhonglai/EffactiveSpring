package spring.state.machine.actions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Service;
import spring.state.machine.StateMachineTemplate;
import spring.state.machine.enums.ApproveEvents;
import spring.state.machine.enums.ApproveStatus;
import state.pattern.service.StatePatternStateMachine;

@Slf4j
@Service
public class RepulseAction implements Action<ApproveStatus, ApproveEvents> {

    @Override
    public void execute(StateContext<ApproveStatus, ApproveEvents> context) {
        log.info("通知审批人：" +
                "您的申请已驳回，备注" + StateMachineTemplate.getRequest(context));
    }
}
