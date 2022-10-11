package per.eicho.demo.designpattern.behavioral;

import java.util.Objects;

import per.eicho.demo.designpattern.annotation.GoFDesignPattern;

/**
 * <p>行为模式 - 状态/p>
 * <pre>
 *  State，状态。允许对象拥有不同的状态，在不同状态下其会表现出不同的特性。
 *   
 *  通俗的来说无状态（Stateless）意味着从外部无论何种角度这个“对象”都是稳定的。
 *  不可变类的实例就是典型的无状态对象，基本不实例化的工具类也是<b>无状态的</b>
 * 
 *  与之相对，有状态（Stateful）意味着从外部能够观测到这个“对象”属性或行为的变化，
 *  这意味着这个对象是<b>不稳定</b>的，因为其行为可能会因为其属性变化而发生变化。
 * </pre>
 * 
 * <pre>
 *  当我们设计一个有状态并且根据状态不同行为可能会变化的类时我们通常第一反应是这么设计。
 *  
 *  <code>
 *   if (STATE_A == CurrenState()) {
 *     // do something
 *   } else (STATE_B == CurrenState()) {
 *     // do something
 *   } else {
 *     // do something
 *   }
 *  </code>
 *  
 *  在体量小时，这很OK。试想当函数的数量不是1个而是很多个，状态也不止3个，而是8个甚至更多
 *  时，大量的状态判断语句会严重影响程序可读性，为了解决这个问题，状态模式登场了。
 * </pre>
 * 
 * <pre>
 *  状态模式，通过把对应“程序逻辑”封装到对应的状态里。大量判断状态的if else语句被移除。
 *  糅合在一个方法里的多个逻辑块也被分配到各自的状态里。
 * </pre>
 */
@GoFDesignPattern
final class StatePatternSample {
    
    /** 对讲机 */
    static final class Talkie {

        Talkie () {
            this(false);
        }

        Talkie (boolean secureMode) {
            state = secureMode ? new SecureTalkieState() : new NormalTalkieState();
        }

        TalkieState state;
        
        /** 测试对讲机是否是安全对话模式 */
        public boolean isSecureModeOn() {
            return state instanceof SecureTalkieState;
        }

        /** 切换到安全对话模式 */
        public void turnOnSecureMode() {
            if (isSecureModeOn()) return;
            state = new SecureTalkieState();
        }

        /** 切换到普通模式 */
        public void turnOnNormalMode() {
            if (!isSecureModeOn()) return;
            state = new NormalTalkieState();
        }

        /** 发送消息 */
        public void send(String message) {
            Objects.nonNull(message);
            state.send(message);
        }

        /** 接收消息 */
        public String receive(byte[] bytes) {
            return state.receive(bytes);
        }

        interface TalkieState {
            void send(String message);
            String receive(byte[] bytes);
        }

        private static final class NormalTalkieState implements TalkieState {

            @Override
            public void send(String message) {
                System.out.println("发送普通消息：" + message);
            }

            @Override
            public String receive(byte[] bytes) {
                final String message = new String(bytes);
                System.out.println("接受普通消息：" + message);
                return message;
            }
        }
        
        private static final class SecureTalkieState implements TalkieState {

            private String encode(String message) {
                return "加密后的信息:" + message; 
            }

            private String decode(byte[] bytes) {
                return new String(bytes).substring("加密后的信息:".length());
            }

            @Override
            public void send(String message) {
                System.out.println("发送加密消息：" + encode(message));
            }

            @Override
            public String receive(byte[] bytes) {
                final String message = decode(bytes);
                System.out.println("接受加密消息：" + message);
                return message;
            }
        }
    }

    public static void main(String[] args) {
        final Talkie talkie = new Talkie();
        talkie.send("普通消息");
        talkie.receive("普通消息".getBytes());
        talkie.turnOnSecureMode();
        talkie.send("加密消息");
        talkie.receive(("加密后的信息:" + "加密消息").getBytes());
    }

    private StatePatternSample() {}
}
