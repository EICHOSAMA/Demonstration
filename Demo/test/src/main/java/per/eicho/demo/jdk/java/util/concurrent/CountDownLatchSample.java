package per.eicho.demo.jdk.java.util.concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>CountDOwnLatch类的使用例</p>
 * 
 * <pre>
 *  {@link CountDownLatch}类是基于AQS框架Shared部分API的提供线程间协作功能的同步器。
 *  比如可以使主控线程等待其他被分配任务的多个线程完成等。
 * </pre>
 */
final class CountDownLatchSample {

    public static class Director {

        public Director() {}

        public String downloadFiles(List<String> files) {
            Objects.requireNonNull(files);
            if (files.isEmpty()) throw new IllegalArgumentException("下载任务不明确。");
            
            final int n = files.size();
            final CountDownLatch doneSignal = new CountDownLatch(n);
            final List<Downloader> downloaders = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                final Downloader downloader = new Downloader(files.get(i), i, doneSignal);
                downloaders.add(downloader);
            }

            final ExecutorService threadPool = Executors.newFixedThreadPool(n);
            for (int i = 0; i < n; i++) {
                threadPool.execute(downloaders.get(i));
            }

            boolean result = false;
            try {
                doneSignal.await();
                result = true;
                // 执行完成
            } catch (InterruptedException e) {
                // 执行失败
                e.printStackTrace();
            }
            threadPool.shutdown();

            if (!result) return "下载失败...";
            // 下载成功
            final StringBuilder sb = new StringBuilder();
            for (Downloader downloader : downloaders) {
                sb.append(downloader.result);
            }

            return sb.toString();
        }

    }
    
    /** 文件下载器 */
    public static class Downloader implements Runnable {

        volatile String result;
        final String file;
        final int idx;
        final CountDownLatch doneSignal;

        public Downloader(String file, int idx, CountDownLatch doneSignal) {
            this.file = file;
            this.idx = idx;
            this.doneSignal = doneSignal;
        }

        @Override
        public void run() {
            System.out.println("第" + idx + "部分下载开始。");
            // 下载...
            try {
                Thread.sleep(200 * file.length());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 下载...

            result = file;
            System.out.println("第" + idx + "部分下载完成。");
            doneSignal.countDown();
        }
    }

    public static void main(String[] args) {
        /*
         * 本例子是一个主管去控制多个文件下载器下载文件，
         * 并等待所有下载任务完成后合并其内容输出。
         */
        final Director director = new Director();
        System.out.println(director.downloadFiles(
            Arrays.asList("测试", "多个下载器", "并行完成下载任务", "由Director统一拼接")));
    }

    private CountDownLatchSample() {}
}
