package spring.state.machine;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;

/**
 * 状态机调用模板，降低业务service使用状态机的成本
 */
public class StateMachineTemplate {

    // 状态机内暂存业务请求数据的key
    private static final String HEADER_REQUEST = "request";

    @SuppressWarnings("unchecked")
    public static <T, S extends Enum, E extends Enum> boolean invokeStateMachine(T payload, E event, StateMachine<S, E> stateMachine) throws Exception {
        // 构造并发送event
        Message<E> eventMsg = MessageBuilder.withPayload(event)
                .setHeader(HEADER_REQUEST, payload)
                .build();
        return stateMachine.sendEvent(eventMsg);
    }

    /**
     * 从状态机中取出请求数据
     * @param context 状态变更上下文
     * @return 请求数据
     */
    public static <S extends Enum, E extends Enum, T> Object getRequest(StateContext<S, E> context){
        return context.getMessageHeader(HEADER_REQUEST);
    }
}
