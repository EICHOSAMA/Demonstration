package per.eicho.demo.designpattern.behavioral;

import java.util.List;
import java.util.Objects;

import per.eicho.demo.designpattern.annotation.GoFDesignPattern;

/**
 * <p>行为模式 - 模板方法模式</p>
 * <pre>
 *  Template method，译做模板方法。模板方法可以说是和抽象类是高度绑定的。
 *  通过在抽象类里指定程序骨架（主控制流），通过设计一些节点（模板方法），
 *  在程序主控制流执行到这些节点时通过调用这些方法把程序控制权下放给具体
 *  实现这些方法的子类。
 * 
 *  这么做能够由抽象类的设计者设计程序骨架，而Customization则通过抽象类的
 *  具体实现子类的开发者去负责实现。
 * </pre>
 * 
 * <pre>
 *  模板方法的实际应用：
 *   - {@link java.util.concurrent.locks.AbstractQueuedSynchronizer}：JUC下大名鼎鼎的AQS就是各类公平锁、不公平锁的骨架。
 *   - {@link java.io.InputStream} 
 * </pre>
 * 
 * <pre>
 *  下例就定义了一个简单的业务执行的“骨架”，其中业务特定的部分做成了模板方法，
 *  其余通用部分则是做成骨架。不难看出模板方法对于规范化、流程化、标准化处理流程
 *  是非常非常重要的一种设计模式，这也非常考验“骨架”设计者的设计能力。
 * </pre>
 */
@GoFDesignPattern
final class TemplateMethodPatternSample {

    static interface MailAddressLoadService {
        public List<String> loadDirectorMailAddressesByName(String service);
    }

    static interface NotificationService {
        public void notifyProcessStarted(List<String> addresses);
        public void notifyProcessEnded(List<String> addresses);
    }

    static abstract class AbstractBussinessProcess {

        MailAddressLoadService mailAddressLoadService;
        NotificationService notificationService;

        static class Caller {
            String token;
            String service;
        }

        /** 骨架方法之一：验证调用方调用的合法性（权力、Token、令牌） */
        private boolean verifyToken(Caller caller) {
            return "合法Token".equals(caller.token);
        }

        /** 骨架方法之二：为指定Caller拉取其业务相关的主管邮件列表。 */
        private List<String> loadDirectorMailAddresses(Caller caller) {
            assert(caller != null && caller.service != null);
            final String service = caller.service;
            final List<String> directorMailAddresses = 
                mailAddressLoadService.loadDirectorMailAddressesByName(service); 
            return directorMailAddresses;
        }

        /** 骨架方法之：在事前准备执行完成后立即执行，通知相关主管处理开始。 */
        private void notifyDirectorProcessStarted(List<String> addresses) {
            assert(addresses != null && !addresses.isEmpty());
            notificationService.notifyProcessStarted(addresses);
        }

        /** 骨架方法之：在主逻辑执行结束后立即执行，通知相关主管处理结束。 */
        private void notifyDirectorProcessEnded(List<String> addresses) {
            assert(addresses != null && !addresses.isEmpty());
            notificationService.notifyProcessEnded(addresses);
        }

        /** 模板方法之一：在执行之前被调用，在此方法做一些事前处理。 */
        abstract void beforeExecute(Caller caller);

        /** 模板方法之二：在执行主逻辑时被调用，在此方法做核心业务处理。 */
        abstract void execute(Caller caller);

        /** 模板方法之三：在执行完成之后被调用，在此方法做一些事后处理。 */
        abstract void afterExecute(Caller caller);

        public void run(Caller caller) {
            Objects.nonNull(caller);
            Objects.nonNull(caller.service);

            if (!verifyToken(caller)) throw new IllegalCallerException("无效令牌");
            final List<String> directorMailAddresses = loadDirectorMailAddresses(caller);

            beforeExecute(caller);
            notifyDirectorProcessStarted(directorMailAddresses);
            execute(caller);
            notifyDirectorProcessEnded(directorMailAddresses);
            afterExecute(caller);
        }
    }

    private TemplateMethodPatternSample() {}
}
