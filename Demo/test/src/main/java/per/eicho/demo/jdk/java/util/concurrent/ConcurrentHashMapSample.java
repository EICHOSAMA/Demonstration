package per.eicho.demo.jdk.java.util.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>ConcurrentHashMap类的使用例</p>
 * 
 * <pre>
 *  {@link java.util.concurrent.ConcurrentHashMap}是 {@link java.util.HashMap}的一个
 *  并发场景下使用的线程安全版。相比 {@link java.util.Collections#synchronizedMap(java.util.Map)}
 *  包装的同步Map，ConcurrentHashMap在读写时都能保持非常高的性能。
 * 
 *  其基本原理是通过对资源分段（块），分段（块）加锁进行操作的思想来提高并发性能。
 *  也就是说资源的粒度在简单地锁整个资源被细分为锁一部分资源。类似的有数据库表锁和行锁。
 *  行锁的粒度就跟细。
 * </pre>
 * 
 * <pre>
 *  早期ConcurrentHashMap内部使用分段锁（Segment Lock）来实现。
 *  现在因为一些兼容性原因（serializing）依然能在其内部看到其身影。
 *  <code>
 *    static class Segment<K,V> extends ReentrantLock implements Serializable { ... }
 *  </code>
 * 
 *  Java8起，ConcurrentHashMap放弃了分段锁，改为Node头数组+链表（红黑树）的方式实现。
 *  资源细分的粒度也由“Segment（段）”变为“LinkedList Header / Balance Tree Root”
 * </pre>
 * 
 * <pre>
 *  扩容机制：
 *   Node数组里的Node可以是链表头也可以是红黑树的根。当链表里的节点数量超过TREEIFY_THRESHOLD（8）
 *   意味着同一个桶里的节点（资源）过多，就会触发扩容检测。
 *     - 如果Node数组小于MIN_TREEIFY_CAPACITY（64）就会把Node数组的容量倍增。
 *     - 反之就会转化该链表为红黑树（TreeBin）
 * </pre>
 */
final class ConcurrentHashMapSample {
    
    public static void main(String[] args) throws InterruptedException {
        final ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();
        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        final int test = 55;
        for (int i = 0; i < 100; i++) {
            final int num = i;
            executorService.execute(() -> {
                concurrentHashMap.put(Integer.valueOf(num % test), "测试" + num);
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        concurrentHashMap.forEach((key, val) -> {
            System.out.println(key + ":" + val);
        });
    }

    private ConcurrentHashMapSample() {}
}
