package per.eicho.demo.jdk.java.util.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>主题线程工厂 - 样例类</p>
 * 
 * <pre>
 *  一个通用的搭配线程池使用的线程工厂类，可以为线程提供统一的主题命名。
 *  命名之后的线程在调查时会很方便。
 * </pre>
 */
public final class ThemeThreadFactoryImpl implements ThreadFactory {

    // 池数量计数器1-based
    private final static AtomicInteger factoryCounter = new AtomicInteger(0);
    private final String namePrefix;
    private final AtomicInteger threadCounter = new AtomicInteger(0);

    ThemeThreadFactoryImpl(String theme) {
        // 参数检查
        theme = theme == null ? "NoTheme" : theme;
        // 线程名前缀 "Pool-应用已创建池数量-[theme]-池内线程数量"
        namePrefix = 
            "Pool-" + factoryCounter.incrementAndGet() + "-[" + theme + "]-";
    }

    @Override
    public Thread newThread(Runnable r) {
        final Thread t = new Thread(r,  namePrefix + threadCounter.incrementAndGet());
        return t;
    }

    public static void main(String[] args) {
        final ThreadFactory downloaderTF =
            new ThemeThreadFactoryImpl("downloader");
        final Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        downloaderTF.newThread(runnable).start();
        downloaderTF.newThread(runnable).start();
        downloaderTF.newThread(runnable).start();

        final ThreadFactory calculatorTF =
            new ThemeThreadFactoryImpl("calculator");        
        calculatorTF.newThread(runnable).start();
        calculatorTF.newThread(runnable).start();
        calculatorTF.newThread(runnable).start();        
    }
}
