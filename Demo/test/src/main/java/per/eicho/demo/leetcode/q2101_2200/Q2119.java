package per.eicho.demo.leetcode.q2101_2200;

/**
 * <p>2119. A Number After a Double Reversal 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/a-number-after-a-double-reversal/">
 *   2119. A Number After a Double Reversal</a>
 */
public final class Q2119 {
    public boolean isSameAfterReversals(int num) {
        // 1. 0 <= num <= 106
        if (num == 0) return true;
        return num % 10 != 0;
    }
}
