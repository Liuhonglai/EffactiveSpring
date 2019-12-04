package state.pattern.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import state.pattern.enums.ApproveEvents;
import state.pattern.service.status.*;

import javax.annotation.Resource;

@Slf4j
@Data
@Scope("prototype")
@Service
public class StatePatternStateMachine implements InitializingBean {

    @Resource
    private InitStatus initStatus;
    @Resource
    private ToBeApprovedStatus toBeApprovedStatus;
    @Resource
    private ApprovedStatus approvedStatus;
    @Resource
    private RepulseStatus repulseStatus;

    private IStatus curStatus;

    @Override
    public void afterPropertiesSet() throws Exception {
        curStatus = initStatus;
    }

    public boolean sendEvent(ApproveEvents events, Object payload) throws Exception {
        switch (events){
            case APPLY:
                return curStatus.apply(this, payload);
            case APPROVER_REPULSE:
                return curStatus.repulse(this, payload);
            case APPROVER_APPROVAL:
                return curStatus.approve(this, payload);
            default:
                return false;
        }
    }
}
