package spring.state.machine.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审批状态
 */
@Getter
@AllArgsConstructor
public enum ApproveStatus {

    INIT("初始化"),
    TO_BE_APPROVED("待批复"),
    APPROVED("已批复"),
    REPULSE("打回");

    private String description;

}
