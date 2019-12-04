package simple.state.machine.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import simple.state.machine.enums.ApproveEvents;
import simple.state.machine.enums.ApproveStatus;

@Slf4j
@Service
public class ApproveService {

    /**
     * 申请
     */
    public ApproveStatus apply(Object payload) throws Exception {
        log.info("您的申请 " + payload + "已提交");
        return ApproveStatus.TO_BE_APPROVED;
    }

    /**
     * 审批通过
     */
    public ApproveStatus approve(Object payload) throws Exception {
        log.info("通知审批人：" +
                "您的申请已通过，备注：" + payload);
        return ApproveStatus.APPROVED;
    }

    /**
     * 打回
     */
    public ApproveStatus repulse(Object payload) throws Exception {
        log.info("通知审批人：" +
                "您的申请已驳回，备注" + payload);
        return ApproveStatus.REPULSE;
    }

}
