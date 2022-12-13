package per.eicho.demo.sample.multithread;

/**
 * <p>做了并发控制但不是线程安全的例子</p>
 * 
 * <pre>
 *  线程安全其实是并发访问控制的一个产物，一旦我们的组件对其内部的数据做了 完善的（注意是完善的）并发访问控制，那么我们可以说这个组件是 （多）线程安全的
 *  那么是不是做了并发访问控制就一定线程安全呢？答案是不一定。如果组件的开发者对于共享数据的并发访问控制逻辑有漏洞，那么其也不能算是线程安全的。
 * </pre>
 */
public final class HaveDoneConcurrencyControlButNotThreadSafeSample {
    
    private static final class MutiThreadCounter {
        volatile int count = 0;
        public int add() { return count++; }
        public synchronized int syncAdd() { return count++; } 
    }

    public static void main(String[] args) throws InterruptedException {
        final MutiThreadCounter counter1 = new MutiThreadCounter();
        final MutiThreadCounter counter2 = new MutiThreadCounter();

        final Thread thread1 = genWorkerThread(counter1, false);
        final Thread thread2 = genWorkerThread(counter1, false);
        final Thread thread3 = genWorkerThread(counter2, true);
        final Thread thread4 = genWorkerThread(counter2, true);
        final Thread[] threads = new Thread[]{thread1, thread2, thread3, thread4};

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        System.out.println(counter1.count);
        System.out.println(counter2.count);
    }

    private static Thread genWorkerThread(MutiThreadCounter counter, boolean useSyncFunc) {
        final Thread thread = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                if (useSyncFunc) {
                    counter.syncAdd();
                } else {
                    counter.add();
                }
            }
        });
        return thread;
    }
}
