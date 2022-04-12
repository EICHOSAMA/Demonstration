package per.eicho.demo.leetcode.q301_400;

/**
 * <p>342. Power of Four 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/power-of-four/">
 *   342. Power of Four</a>
 */
public class Q342 {
    public boolean isPowerOfFour(int num) {
        // 1. -2^31 <= n <= 2^31 - 1
        return (num == 1) || ((num != 0) &&(num % 4 == 0) && isPowerOfFour(num / 4));
    }
}
