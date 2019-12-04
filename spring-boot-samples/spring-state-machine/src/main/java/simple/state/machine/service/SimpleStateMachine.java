package simple.state.machine.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import simple.state.machine.enums.ApproveEvents;
import simple.state.machine.enums.ApproveStatus;

import javax.annotation.Resource;

@Slf4j
@Scope("prototype")
@Service
public class SimpleStateMachine {

    @Resource
    private ApproveService approveService;

    private ApproveStatus curStatus = ApproveStatus.INIT;

    public boolean sendEvent(ApproveEvents events, Object payload) throws Exception {
        switch (curStatus){
            case INIT:
                switch (events){
                    case APPLY:
                        curStatus = approveService.apply(payload);
                        return true;
                    default:
                        return false;
                }
            // 待审批
            case TO_BE_APPROVED:
                switch (events){
                    case APPROVER_APPROVAL:
                        curStatus = approveService.approve(payload);
                        return true;
                    case APPROVER_REPULSE:
                        curStatus = approveService.repulse(payload);
                        return true;
                    default:
                        return false;
                }
            // 已批复
            case APPROVED:
                switch (events){
                    default:
                        return false;
                }
            // 打回
            case REPULSE:
                switch (events){
                    case APPLY:
                        curStatus = approveService.apply(payload);
                        return true;
                    default:
                        return false;
                }
            default:
                return false;
        }
    }
}
