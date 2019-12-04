package spring.state.machine.actions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Service;
import spring.state.machine.StateMachineTemplate;
import spring.state.machine.enums.ApproveEvents;
import spring.state.machine.enums.ApproveStatus;

@Slf4j
@Service
public class ApplyAction implements Action<ApproveStatus, ApproveEvents> {

    @Override
    public void execute(StateContext<ApproveStatus, ApproveEvents> context) {
        log.info("您的申请 " + StateMachineTemplate.getRequest(context) + "已提交");
    }
}
