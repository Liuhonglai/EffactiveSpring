package state.pattern.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审批事件
 */
@Getter
@AllArgsConstructor
public enum ApproveEvents {

    APPLY("申请"),
    APPROVER_APPROVAL("处理人批复"),
    APPROVER_REPULSE("处理人打回");

    private String description;

}
