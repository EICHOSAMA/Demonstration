package per.eicho.demo.leetcode.q101_200;

/**
 * <p>172. Factorial Trailing Zeroes 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/factorial-trailing-zeroes/">172. Factorial Trailing Zeroes</a>
 */
public final class Q172 {
    public int trailingZeroes(int n) {
        // 1. 0 <= n <= 10^4
        int result = 0;
        while (n != 0) {
            n = n / 5;
            result += n;
        }
        return result;
    }
}
