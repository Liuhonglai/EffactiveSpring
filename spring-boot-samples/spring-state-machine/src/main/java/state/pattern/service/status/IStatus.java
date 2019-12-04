package state.pattern.service.status;

import state.pattern.service.StatePatternStateMachine;

public interface IStatus {
    /**
     * 申请
     */
    default boolean apply(StatePatternStateMachine stateMachine, Object payload) throws Exception {
        return false;
    }

    /**
     * 审批通过
     */
    default boolean approve(StatePatternStateMachine stateMachine, Object payload) throws Exception {
        return false;
    }

    /**
     * 打回
     */
    default boolean repulse(StatePatternStateMachine stateMachine, Object payload) throws Exception {
        return false;
    }
}
