package per.eicho.demo.jdk.java.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>Timer类的样例类</p>
 * <pre>
 *  {@link java.util.Timer}类是JDK1.3开始就提供的一种定时任务的工具类。
 *  
 *  使用Timer类需要了解 {@link java.util.TimerTask}抽象类，
 *  这是往一个Timer实例添加任务时的基本单元。
 *  其内部数据结构是package private的一个叫做TaskQueue的东西，其本质是一个小顶堆
 *  比较的属性是TimerTask的nextExecutionTime。
 *  其实现原理是内部新建了一个线程TimerThread，利用这个线程去执行小顶堆堆顶的任务。
 * </pre>
 * 
 * <pre>
 *  知晓其原理后我们可以发现如果任务执行时间过长，单个线程来执行延时任务可能会出现比预想更
 *  晚的延迟，因为前面的任务导致的延迟，所以其实Timer类缺陷挺多那么JDK其实也是知道这件事的
 *  
 *    Java 5.0 introduced the java.util.concurrent package and one of the concurrency 
 *    utilities therein is the ScheduledThreadPoolExecutor which is a thread pool for 
 *    repeatedly executing tasks at a given rate or delay. 
 *    It is effectively a more versatile replacement for the Timer/TimerTask combination,
 *  
 *  所以在Timer文档中也说明了JUC包下的{@link java.util.concurrent.ScheduledThreadPoolExecutor}类是
 *  Timer/TimerTask组合的替代。
 * </pre>
 */
final class TimerSample {

    final static Object lock = new Object();

    private static final class EODMailingTask extends TimerTask {

        @Override
        public void run() {
            // Send Mail Here
            System.out.println("EOD处理之发邮件通知相关主管");
            synchronized(lock) {
                lock.notifyAll();
            }
        }
        
    }
    
    public static void main(String[] args) throws InterruptedException {
        final Timer timer = new Timer("EOD处理");
        final long delay = 10_000; // unit milliseconds 
        timer.schedule(new EODMailingTask(), delay);
        synchronized(lock) {
            lock.wait();
            timer.cancel(); // stop the timer.
        }
        System.out.println("应用程序执行结束");
    }

    private TimerSample() {}
}
