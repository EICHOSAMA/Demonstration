package per.eicho.demo.leetcode.q301_400;

/**
 * <p>397. Integer Replacement 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/integer-replacement/">397. Integer Replacement</a>
 */
public class Q397 {
    public int integerReplacement(int n) {
        // 1. 1 <= n <= 2^31 - 1
        if (n == 1) return 0;
        if (n % 2 == 0) return integerReplacement(n >> 1) + 1; // case: even.
        if (n == 3) return 2;

        // greedy.
        if ((n & 0b011) == 0b011) return integerReplacement((n + 1) >>> 1) + 2;
        return integerReplacement(n - 1)+ 1;
    }
}
