package per.eicho.demo.sample.multithread;

/**
 * <p>多线程数据不一致性错误样例</p>
 * 
 * <pre>
 *  多线程下，如果不做并发访问控制，数据将会以难以预料的方式被修改，
 *  从而出现预料之外的不一致性错误。
 * </pre>
 */
public final class MultithreadDataConsistencyErrorSample {
    
    static int count = 0;

    private static Thread genWorkerThread() {
        final Thread thread = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                count++;
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
