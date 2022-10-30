package per.eicho.demo.jdk.java.util.concurrent;

import java.time.LocalDateTime;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>ScheduledThreadPoolExecutor类的样例类</p>
 * <pre>
 *  {@link java.util.concurrent.ScheduledThreadPoolExecutor}类
 *     - 是{@link java.util.concurrent.ThreadPoolExecutor}类的子类
 *     - 实现了{@link java.util.concurrent.ScheduledExecutorService}接口
 *  是JDK中提供定时任务功能的一个类。是Timer/TimerTask的替代。
 * </pre>
 * 
 * <pre>
 *  ScheduledThreadPoolExecutor提供了定时执行任务、间隔时间重复执行任务的功能。
 *  可以被用来定期对外发送服务器内部状态（如心跳、CPU使用率）等。
 * </pre>
 * 
 * <h2>不支持Cron表达式(クロン表現式)</h2>
 * <pre>
 *  如果需要支持Cron表达式的定时任务，可以选择<a href="https://spring.io/guides/gs/scheduling-tasks/">Spring Task</a>。
 * </pre>
 */
final class ScheduledThreadPoolExecutorSample {

    final static AtomicInteger atomicCounter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ScheduledThreadPoolExecutor sExecutor = new ScheduledThreadPoolExecutor(5);
        ScheduledFuture<?> scheduledFuture = sExecutor.scheduleAtFixedRate(ScheduledThreadPoolExecutorSample::sendHeartBeat, 1L, 2L, TimeUnit.SECONDS);
        
        System.out.println("应用主线程即将睡眠11秒");
        Thread.sleep(11_000);
        System.out.println("应用主线程结束睡眠，应用将结束运行");
        sExecutor.shutdown();
        System.out.println("执行器停止接受新任务");
        scheduledFuture.cancel(false);
        sExecutor.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("应用结束运行");
    }

    private static void sendHeartBeat() {
        System.out.println("第"+ atomicCounter.incrementAndGet() + "次服务器心跳：" + LocalDateTime.now());
    }
    
    private ScheduledThreadPoolExecutorSample() {}
}
