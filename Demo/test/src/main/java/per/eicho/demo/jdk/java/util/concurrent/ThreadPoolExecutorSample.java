package per.eicho.demo.jdk.java.util.concurrent;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>ThreadPoolExecutor类的使用例</p>
 * 
 * <pre>
 *  {@link ThreadPoolExecutor}类是JDK提供给开发者的线程池工具类，
 * </pre>
 */
@SuppressWarnings("unused")
public final class ThreadPoolExecutorSample {
    public static class RunnableWrapper implements Runnable {

        final Runnable r;
        final RetryThenDiscardPolicy policy;

        RunnableWrapper(Runnable r, RetryThenDiscardPolicy policy) { 
            this.r = r; 
            this.policy = policy;
        }

        @Override
        public void run() {
            r.run();
            policy.map.remove(this);
        }
        
        public static RunnableWrapper wrapRunnable(Runnable r, RetryThenDiscardPolicy policy) {
            Objects.nonNull(r);
            Objects.nonNull(policy);
            final RunnableWrapper wrapper = new RunnableWrapper(r, policy);
            return wrapper;
        }
    }

    public static class RetryThenDiscardPolicy implements RejectedExecutionHandler {
        
        final int retryCount;
        /** 
         * RejectedExecutionHandler代码的执行是在任务生产者线程。
         * 任务生产者线程可能是多线程的，所以需要用到线程安全的ConcurrentHashMap。
         */
        final ConcurrentHashMap<Runnable, Integer> map;
        
        public RetryThenDiscardPolicy(int retryCount) { 
            if (retryCount <= 1) throw new IllegalArgumentException("无意义的retryCount");

            this.retryCount = retryCount;
            map = new ConcurrentHashMap<>();
        }

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            
            if (!map.containsKey(r)) {
                final Runnable wrapperR = RunnableWrapper.wrapRunnable(r, this); 
                map.put(wrapperR, retryCount);
                e.execute(wrapperR);
            } else if (map.get(r) > 0) {
                System.out.println("重试任务:" + r);
                map.computeIfPresent(r, (key, cnt) -> --cnt);         
                e.execute(r);
            } else {
                // 重试次数耗尽，丢弃。
                System.out.println("丢弃任务:" + r);
                map.remove(r);
                return;
            }
        }
    }

    /**
     * <p>Case - 核心线程归零时会导致应用直接退出</p>
     * <pre>
     *  不过如果你通过allowCoreThreadTimeOut(boolean)方法，设置允许超时回收的话，
     *  核心工作线程的数量在没有任务执行时逐步被回收，如果没有其他运行中的线程保护，核心线程归零时会导致应用直接退出。
     * </pre>
     */
    private static void testSetAllowCoreThreadTimeOut2TrueCauseAppExit() {
        final ThreadPoolExecutor tpExecutor = new ThreadPoolExecutor(
            5, 
            10, 
            10, TimeUnit.SECONDS, 
            new ArrayBlockingQueue<>(10_0000)); 
        tpExecutor.allowCoreThreadTimeOut(true);

        tpExecutor.execute(() -> { System.out.println(Thread.currentThread().getName()); });
    }

    /**
     * <p>Case - 扩展非核心线程逻辑导致的插队问题</p>
     * <pre>
     *  在workQueue满任务时，新提交的任务会直接交予新增非核心线程执行，导致插队。
     * </pre>
     */    
    private static void testCutInLine() {
        final ThreadPoolExecutor tpExecutor = new ThreadPoolExecutor(
            5, 
            10, 
            10, TimeUnit.SECONDS, 
            new ArrayBlockingQueue<>(5)); 

        for (int i = 0; i < (5 + 5 + 1); i++) {
            final int idx = i;
            tpExecutor.submit(() -> {
                System.out.println("第" + idx + "个任务执行开始");
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                }
                System.out.println("第" + idx + "个任务执行结束。");
            });
        }
    }

    private static void testRetryThenDiscardPolicy() throws InterruptedException {
        final ThreadPoolExecutor tpExecutor = new ThreadPoolExecutor(
            5, 
            6, 
            10, TimeUnit.SECONDS, 
            new ArrayBlockingQueue<>(4));
        tpExecutor.setRejectedExecutionHandler(new RetryThenDiscardPolicy(3));
        
        for (int i = 0; i < (5 + 5 + 1); i++) {
            final int idx = i;
            tpExecutor.submit(() -> {
                System.out.println("第" + idx + "个任务执行开始");
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                }
                System.out.println("第" + idx + "个任务执行结束。");
            });
        }

        tpExecutor.shutdown();
        tpExecutor.awaitTermination(10, TimeUnit.SECONDS);
    }
    
    public static void main(String[] args) throws InterruptedException {
        testRetryThenDiscardPolicy();
    }
}
