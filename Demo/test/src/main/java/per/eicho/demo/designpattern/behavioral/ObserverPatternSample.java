package per.eicho.demo.designpattern.behavioral;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicBoolean;

import per.eicho.demo.designpattern.annotation.GoFDesignPattern;

/**
 * <p>行为模式 - 观察者模式</p>
 * <pre>
 *  观察者，Observer。
 * 
 *  观察者模式，与之相对的概念有轮询、监视（或是盯梢）。
 *    - 轮询：对于某个事物的状态感兴趣，每过一段时间都去检查这个事物的状态，
 *           依赖查询到的状态做一些回应。比如自旋锁、秒杀活动开始前疯狂F5的买家。
 *    - 监视：男孩A喜欢女孩B，有事没事盯着女孩的SNS，女孩发“明天过生好想要个XXX”，
 *           于是男孩第二天送了女孩想要的XXX。也是依赖获取到的信息做一些回应。
 *  可以看到观察者模式相对概念的特征是需要对某些情报感兴趣的<b>人组织或软件组件</b>
 *  自发去主动获取这些情报。那么与之相对观察者模式是什么呢？
 *    - 观察者模式：某情报一手源（信息源头）说，只要你们留联系方式，有情况立即通知你们。
 *                 于是乎这些对情报感兴趣的人或组织就从主动询问变成了被动就接收。
 *  我们都知道盯梢是很费时费力的，那么有个内线可以在有一手信息时主动联系则是很美的一件事。
 *  观察者模式就是做了这么个事。
 * </pre>
 * 
 * <pre>
 *  下面列举几个观察者模式在实际的运用：
 *    - GUI应用：MVC大家都知道，在M（数据）发生变动时，如果不能及时反映到V（画面上）就会出现数据一致性问题。
 *       如果利用观察者模式就可以接到数据变动的Event，Controller可以立即做出反应，利用新数据重绘画面。
 *    - 异步任务回调：onComplete相信大家也不陌生，任务完成时，通过回调地方式通知外部任务完成。
 * </pre>
 * 
 * <pre>
 *   观察者模式是实际开发中会比较常遇到的一种设计模式，也比较简单，推荐掌握。
 * 
 *   Java1.0 ~ Java8: {@link java.util.Observable}，@Deprecated(since="9")
 *   Java9 ~ : {@link java.util.concurrent.Flow}
 * </pre>
 */
@GoFDesignPattern
final class ObserverPatternSample {

    /** 数据模型 */
    static final class Event {
        /** 活动地址 */
        final String address;
        /** 活动时间 */
        final LocalDateTime dateTime;
        /** 年龄限制 */
        final int ageLimition; 

        Event(String address, LocalDateTime dateTime, int ageLimition) {
            this.address = address;
            this.dateTime = dateTime;
            this.ageLimition = ageLimition;
        }
    }

    /** 活动情报发布者 */
    static final class EventPublisher implements Publisher<Event> {

        private final ExecutorService publisherExecutorService = Executors.newSingleThreadExecutor();        

        private class EventSubscription implements Subscription {

            private final Subscriber<? super Event> subscriber;
            private final ExecutorService executorService;
            private final AtomicBoolean isCancelled;

            EventSubscription(Subscriber<? super Event> subscriber, ExecutorService executorService) {
                this.subscriber = subscriber;
                this.executorService = executorService;
                isCancelled = new AtomicBoolean(false);
            }

            @Override
            public void request(long n) {
                throw new UnsupportedOperationException("请勿主动请求数据。");
            }

            @Override
            public void cancel() {
                if (isCancelled.get()) return;

                synchronized(subscriptions) {
                    subscriptions.remove(this);
                    isCancelled.set(true);
                }
            }

            public void asyncPushEvent(Event event) {
                assert(isCancelled.get() == false) : "取消时会从subscriptions里移除不会接收到push命令。";
                assert(null != event) : "外层Publisher已经检查。";
                // 提交任务给executorService
                executorService.execute(() -> {
                    subscriber.onNext(event);
                });
            }
        } 

        final List<EventSubscription> subscriptions;
        final ExecutorService executorService;

        EventPublisher() {
            executorService = new ThreadPoolExecutor(5, 10, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));
            subscriptions = Collections.synchronizedList(new ArrayList<>());
        }

        @Override
        public void subscribe(Subscriber<? super Event> subscriber) {
            /** 注册为订阅者 */
            Objects.nonNull(subscriber);
            final EventSubscription subscription = new EventSubscription(subscriber, executorService);
            subscriptions.add(subscription);
            subscriber.onSubscribe(subscription);
        }

        /** 表示从外部新接收到来一个Event */
        public void incomingEvent(Event event) {
            Objects.nonNull(event);
            // 提交任务到Publisher专用线程
            publisherExecutorService.execute(() -> {
                // 任务就干一件事，通知
                subscriptions.forEach((subscription) -> {
                    subscription.asyncPushEvent(event);
                });
            });
        }
        
        /** 停止Publishing服务 */
        public void stopPublishing() {
            System.out.println("Publisher正在停止");
            publisherExecutorService.shutdown(); // 停止接收新任务，老任务会继续执行。
            while(!publisherExecutorService.isTerminated()) {}
            executorService.shutdown();
            while(!executorService.isTerminated()) {}
            System.out.println("Publisher已经停止");
        }
    }

    /** 活动情报订阅者 */
    static final class EventSubscriber implements Subscriber<Event> {

        final String role;

        public EventSubscriber(String role) {
            Objects.nonNull(role);
            this.role = role;
        }

        @SuppressWarnings("unused")
        private volatile Subscription subscription;

        @Override
        public void onSubscribe(Subscription subscription) {
            this.subscription = subscription;
        }

        @Override
        public void onNext(Event event) {
            System.out.println(role +  "收到新活动:" + event.address + "-" + event.ageLimition + "-" + event.dateTime);
        }

        @Override
        public void onError(Throwable throwable) {
            // If a Publisher encounters an error that does not allow 
            // items to be issued to a Subscriber, 
            // that Subscriber receives onError
            System.out.println("Method Not Used");
        }

        @Override
        public void onComplete() {
            // Otherwise, when it is known that no further messages 
            // will be issued to it, a subscriber receives onComplete
            subscription = null;
        }
    }

    public static void main(String[] args) {
        final EventPublisher eventPublisher = new EventPublisher();
        final EventSubscriber subscriber1 = new EventSubscriber("黄牛");
        final EventSubscriber subscriber2 = new EventSubscriber("法外狂徒张三");
        
        eventPublisher.subscribe(subscriber1);
        eventPublisher.subscribe(subscriber2);

        eventPublisher.incomingEvent(new Event("第六大街", LocalDateTime.now(), 18));
        eventPublisher.stopPublishing();
    }

    
    private ObserverPatternSample() {}
}
