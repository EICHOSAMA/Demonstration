package per.eicho.demo.designpattern.creational;

import per.eicho.demo.designpattern.annotation.GoFDesignPattern;

/**
 * <p>创建型模式 - 单例模式</p>
 * <pre>
 *  单例模式主要意图是保证一个类仅有一个实例，并对外提供一个访问这个仅有实例的方法。
 * </pre>
 */
@GoFDesignPattern
final class SingletonPatternSample {

    /**
     * <p>单例模式 - 双重检查实现</p>
     * <pre>
     *  最小化同步块影响范围、高速、线程安全的懒汉单例实现
     * </pre>
     */
    static class DoubleCheckedLockingSingleton {
        /**
         * <p>私有类成员变量，用于存放唯一实例</p>
         * volatile是关键，保证了高并发时，其他线程对初始化线程写入操作的可见性。
         * Ref - "... This means that changes to a volatile variable are always visible to other threads."
         * https://docs.oracle.com/javase/tutorial/essential/concurrency/atomic.html
         */
        private volatile static DoubleCheckedLockingSingleton sharedInstance;

        /** 构造器私有化 */
        private DoubleCheckedLockingSingleton() {}

        /** 对外公开的获取唯一实例的方法 */
        public DoubleCheckedLockingSingleton getInstance() {
            /*
             * 第一重检查，除了最开始同时抢锁的那些线程之外，后续进入到本方法的线程
             * 都会被第一次检查挡住，直接返回现有实例。
             */
            if (sharedInstance == null) {
                // 当共享实例还未被实现时，需要实例化共享实例。

                // 高并发时，通过竞争类锁保证同时只有一个线程会操作sharedInstance
                synchronized (DoubleCheckedLockingSingleton.class) {
                    /*
                     * 第二重检查，一个线程完成初始化操作之后，退出同步块，释放class的重入锁。
                     * 剩余等待锁的线程便能一个一个进入到这个同步块，并由第二次检查，
                     * 可以有效放置这些线程再次对实例化共享实例。
                     */
                    if (sharedInstance == null) {
                        sharedInstance = new DoubleCheckedLockingSingleton();
                    }   
                }
            }

            return sharedInstance;
        }

    }
    
    private SingletonPatternSample() {}
}
