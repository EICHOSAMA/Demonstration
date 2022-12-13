package per.eicho.demo.sample.multithread;

import java.util.concurrent.atomic.AtomicReference;

public final class CustomizeYourOwnLockSampe {
    
    private static final class CustomizeLock {

        private final AtomicReference<Thread> ownerThread = new AtomicReference<>();

        public void lock() {
            final Thread current = Thread.currentThread();
            for (;;) {
                if (null == ownerThread.compareAndExchange(null, current)) break;
            }
        }

        public void unlock() {
            final Thread current = Thread.currentThread();
            if (!ownerThread.get().equals(current)) throw new IllegalStateException("Current thread is not the owner thread of this lock instance.");
            ownerThread.set(null);
        }

    }

    static int count = 0;
    static CustomizeLock lock = new CustomizeLock();

    private static Thread genWorkerThread() {
        final Thread thread = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                lock.lock();
                count++;
                lock.unlock();
            }
        });
        return thread;
    }

    public static void main(String[] args) throws InterruptedException {
        final Thread thread1 = genWorkerThread();
        final Thread thread2 = genWorkerThread();

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(count);
    }
}
