package per.eicho.demo.leetcode.q1101_1200.q1116;

import java.util.function.IntConsumer;

/**
 * <p>1116. Print Zero Even Odd 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/print-zero-even-odd/">
 *   1116. Print Zero Even Odd</a>
 * @see <a href="https://docs.oracle.com/javase/jp/8/docs/api/java/lang/Object.html">
 *   [Oracle Java SE8 Doc] Object Class</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/atomic.html">
 *   [Oracle Java Tutorials] Atomic Access</a>
 */
public final class Q1116UsingSynchronized {
    class ZeroEvenOdd {
        private int n;
        private volatile int flag = 1;
        
        public ZeroEvenOdd(int n) {
            this.n = n;
        }
    
        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                synchronized (this) {
                    while (flag % 2 == 0) wait();
                    printNumber.accept(0);
                    flag++;
                    notifyAll();
                }
            }
        }
    
        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i += 2) {
                synchronized (this) {
                    while (flag % 4 != 0) wait();
                    printNumber.accept(i);
                    flag++;
                    notifyAll();
                }
            }
        }
    
        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                synchronized (this) {
                    while (flag % 4 != 2) wait();
                    printNumber.accept(i);
                    flag++;
                    notifyAll();
                }
            }
        }
    }
}
