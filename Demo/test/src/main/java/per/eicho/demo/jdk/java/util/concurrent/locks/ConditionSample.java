package per.eicho.demo.jdk.java.util.concurrent.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Condition接口的样例类</p>
 * <pre>
 *  {@link java.util.concurrent.locks.Condition}接口之于{@link java.util.concurrent.locks.Lock}
 *  就相当于Object类的Monitor方法集（Wait、Notify、NotifyAll）之于内置锁（synchronized）
 * </pre>
 * 
 * <pre>
 *   方法对标：
 *    - {@link Condition#await()}系列方法对标{@link Object#wait()}系列方法
 *    - {@link Condition#signal()}方法对标{@link Object#notify()}方法
 *    - {@link Condition#signalAll()}方法对标{@link Object#notifyAll()}方法
 * </pre>
 */
final class ConditionSample {
    
    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(2);
        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        executorService.execute(() -> {
            final Thread thread = Thread.currentThread();
            lock.lock();
            System.out.println(thread.getName() + "线程已获取锁");
            try {
                System.out.println(thread.getName() + "持锁睡眠1秒等待其他线程因锁竞争进入阻塞状态。");
                // 等待其他线程竞争锁状态就绪
                Thread.sleep(1000);
                System.out.println(thread.getName() + "或叫醒其他线程等待状态的线程");
                condition.signalAll();                
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
                
            try {
                System.out.println(thread.getName() + "其他线程为阻塞状态时，主动进入等待态并释放锁。");
                condition.await();
                System.out.println(thread.getName() + "线程已被signal唤醒并重新获取到锁。");
                condition.signalAll();
                System.out.println(thread.getName() + "唤醒其他等待中的线程，叫他们起来准备竞争锁了");
                lock.unlock();
                System.out.println(thread.getName() + "已经释放锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        });

        executorService.execute(() -> {
            final Thread thread = Thread.currentThread();
            lock.lock();
            System.out.println(thread.getName() + "线程已获取锁");
            try {
                System.out.println(thread.getName() + "持锁睡眠1秒等待其他线程因锁竞争进入阻塞状态。");
                // 等待其他线程竞争锁状态就绪
                Thread.sleep(1000);
                System.out.println(thread.getName() + "或叫醒其他线程等待状态的线程");
                condition.signalAll();                  
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            try {
                System.out.println(thread.getName() + "其他线程为阻塞状态时，主动进入等待态并释放锁。");
                condition.await();
                System.out.println(thread.getName() + "线程已被signal唤醒并重新获取到锁。");
                condition.signalAll();
                System.out.println(thread.getName() + "唤醒其他等待中的线程，叫他们起来准备竞争锁了");
                lock.unlock();
                System.out.println(thread.getName() + "已经释放锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("分线程均执行结束");
        System.out.println("Application will exit soon.");
    }

    private ConditionSample() {}
}
