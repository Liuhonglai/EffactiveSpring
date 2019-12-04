package state.pattern.service.status;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import simple.state.machine.enums.ApproveStatus;
import state.pattern.service.StatePatternStateMachine;

@Slf4j
@NoArgsConstructor
@Service
public class RepulseStatus implements IStatus{

    @Override
    public boolean apply(StatePatternStateMachine stateMachine, Object payload) throws Exception {
        log.info("您的申请 " + payload + "已提交");
        stateMachine.setCurStatus(stateMachine.getToBeApprovedStatus());
        return true;
    }
}
