package per.eicho.demo.jdk.java.util.concurrent.locks;

/**
 * <p>死锁例</p>
 * 
 * <pre>
 *  死锁，线程间互相持有资源时竞争对方资源并且没有资源放手机制会导致线程死锁。
 *  
 *  如有甲乙两人和枪、弹夹两种资源。两人距离资源不同使得甲乙两人会分别先后去
 *  抢占两种资源（甲：枪→弹夹， 乙：弹夹→枪） 。此时两人就出现了分别单独持
 *  有某一种资源但是缺无法获取到另一种资源的窘境。
 * </pre>
 */
final class DeadLockSample {

    static abstract class Resource {

        abstract String name(); 
        
        synchronized void hold(Thread people, Resource nextTarget) {
            System.out.println(people.getName() + "抢占到资源：" + name());
            if (nextTarget != null) {
                sleep(1000); // 资源之间等距
                nextTarget.hold(people, null);
            } else {
                System.out.println(people.getName() + "抢占到所有资源。");
            }
        }
    }

    /** 枪 */
    static final class Gun extends Resource {
        @Override
        String name() { return "枪"; }
    } 

    /** 弹夹 */
    static final class Mag extends Resource {
        @Override
        String name() { return "弹夹"; }        
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        final Gun gun = new Gun();
        final Mag mag = new Mag();

        final Thread people1 = new Thread(() -> {
            final Thread t = Thread.currentThread();
            sleep(1000);
            gun.hold(t, mag);
        }, "甲");

        final Thread people2 = new Thread(() -> {
            final Thread t = Thread.currentThread();
            sleep(1500);
            mag.hold(t, gun);
        }, "乙");

        people1.start();
        people2.start();
    }
}
