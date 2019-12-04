package state.pattern.service.status;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import simple.state.machine.enums.ApproveStatus;
import state.pattern.service.StatePatternStateMachine;

@Slf4j
@Service
public class ToBeApprovedStatus implements IStatus{

    @Override
    public boolean approve(StatePatternStateMachine stateMachine, Object payload) throws Exception {
        log.info("通知审批人：" +
                "您的申请已通过，备注：" + payload);
        stateMachine.setCurStatus(stateMachine.getApprovedStatus());
        return true;
    }

    @Override
    public boolean repulse(StatePatternStateMachine stateMachine, Object payload) throws Exception {
        log.info("通知审批人：" +
                "您的申请已驳回，备注" + payload);
        stateMachine.setCurStatus(stateMachine.getRepulseStatus());
        return true;
    }
}
