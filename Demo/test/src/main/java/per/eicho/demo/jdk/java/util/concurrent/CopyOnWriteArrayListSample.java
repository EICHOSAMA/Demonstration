package per.eicho.demo.jdk.java.util.concurrent;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>CopyOnWriteArrayList类的使用例</p>
 * <pre>
 *  {@link java.util.concurrent.CopyOnWriteArrayList}是{@link java.util.ArrayList}的
 *  一个线程安全版实现。如其名CopyOnWrite+Array+List，CopyOnWriteArrayList是List的一种实现，
 *  内部和ArrayList一样，是依赖于Java数组（数据结构）。其特征是会在执行所有写相关操作（Add、Remove等）时
 *  替换内部数据结构为一个全新的数组对象。当然这个数组对象内部的所有元素都是引用类型，
 *  Copy时这些类型本身不会被拷贝。
 * </pre>
 *  
 * <pre>
 *  CopyOnWriteArrayList的应用场景：
 *   不难理解CopyOnWriteArrayList每次写操作都会有Copy操作，如果大量使用写操作会导致性能低下。
 *   那么CopyOnWriteArrayList这个类呢，就主要是在多读少写的场景下使用。
 * </pre>
 * 
 * <pre>
 *  需要注意的是，CopyOnWriteArrayList在写操作时加锁，读操作时没有加锁。
 *  这意味着多个线程不能同时执行写操作，但一个线程执行写操作和多个线程执行读操作
 *  是可行的，那么就要注意一个缓存过时的问题。什么意思呢？
 *  
 *    A线程：--------------> [remove(x)              ] --------->
 *    B线程：s = size() ---> [doSomething()              ] -----> get(s)
 *  
 *  B线程提前获取CopyOnWriteArrayList的size()并用一个变量s存储（实质上就是缓存）
 *  A线程在B线程缓存size之后，对线程执行写操作（例中remove）。
 *  此时B线程在处理完某些事（doSomething）之后，去获取第s个元素get就有可能出现越界问题。
 *  这本质是缓存过时的问题。
 * </pre>
 */
final class CopyOnWriteArrayListSample {

    public static void main(String[] args) throws InterruptedException {
        // 模拟缓存失效
        final CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 5; i++) {
            copyOnWriteArrayList.add(i);
        }
        
        final ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> {
            int s = copyOnWriteArrayList.size(); // 缓存
            
            // doSomething
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                final Integer element = copyOnWriteArrayList.get(s - 1);
                System.out.println(element);
            } catch (Throwable e) {
                // 缓存过时导致 
                // java.lang.ArrayIndexOutOfBoundsException: 
                //    Index 4 out of bounds for length 4
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            copyOnWriteArrayList.remove(0);
        });
        
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println(copyOnWriteArrayList);
    }
    
    private CopyOnWriteArrayListSample() {}
}
