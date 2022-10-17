package per.eicho.demo.jdk.java.util.concurrent;

/**
 * <p>BlockingQueue接口的使用例</p>
 * <pre>
 *  {@link java.util.concurrent.BlockingQueue}接口又名“阻塞队列”，什么意思呢？
 *   
 *    A Queue that additionally supports operations that 
 *       - wait for the queue to become non-empty when retrieving an element, 
 *       - and wait for space to become available in the queue when storing an element.
 *    
 *   文档的第一句就很明确说明了，BlockingQueue是在Queue基础上支持了两个新特性。
 *       - 获取元素时：会阻塞到从队列成功获取元素。
 *       - 添加元素时：会阻塞到成功向队列添加元素。
 *   
 *   阻塞队列的主要应用在“生产者 - 消费者”场景下。典型的
 *    {@link java.util.concurrent.ThreadPoolExecutor#ThreadPoolExecutor(int, int, long, java.util.concurrent.TimeUnit, java.util.concurrent.BlockingQueue, java.util.concurrent.ThreadFactory, java.util.concurrent.RejectedExecutionHandler)}
 * </pre>
 * 
 * <pre>
 *  BlockingQueue的主要实现有以下几个
 *   - {@link java.util.concurrent.ArrayBlockingQueue}: 
 *        内部数据结构为数组的有界阻塞队列，FIFO
 *   - {@link java.util.concurrent.LinkedBlockingDeque}: 
 *        内部数据结构为链表的可无界可有界（An optionally-bounded）阻塞队列
 *        界值可通过参数capacity设置，默认MAX Integer非常大可以认为是无界，FIFO
 *   - {@link java.util.concurrent.PriorityBlockingQueue}: 
 *        与{@link java.util.PriorityQueue}（实质是堆）的取物顺序，并不是FIFO的。
 * </pre>
 */
final class BlockingQueueSample {
    
    /* Concrete类的演示参考{Concrete类名}Sample.java文件 */

    private BlockingQueueSample() {}
}
