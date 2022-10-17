package per.eicho.demo.jdk.java.util.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>ConcurrentLinkedQueue类的使用例</p>
 * <pre>
 *  {@link java.util.concurrent.ConcurrentLinkedQueue}类是{@link java.util.Queue}接口的一个实现。
 *  这个实现是并发环境下应该选用的队列实现类。有队列的FIFO、和其他并发集合一样不允许使用Null等特性。
 *  
 *  ConcurrentLinkedQueue内部实现是基于CAS的，比如多个线程竞争同时添加节点，那么竞争失败的线程会重试。
 * </pre>
 */
final class ConcurrentLinkedQueueSample {
    
    public static void main(String[] args) throws InterruptedException {
        final ConcurrentLinkedQueue<Integer> clq = new ConcurrentLinkedQueue<>();
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            final int num = i;
            executorService.execute(() -> {
                clq.offer(Integer.valueOf(num));
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        while (!clq.isEmpty()) {
            System.out.println(clq.poll());
        }
    }

    private ConcurrentLinkedQueueSample() {}
}
