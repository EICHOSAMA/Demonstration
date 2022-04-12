package per.eicho.demo.leetcode.q301_400;

/**
 * <p>326. Power of Three 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/power-of-three/">
 *   326. Power of Three</a>
 */
public class Q326 {
    public boolean isPowerOfThree(int n) {
        // 1. -2^31 <= n <= 2^31 - 1
        return (n == 1) || ((n != 0) &&(n % 3 == 0) && isPowerOfThree(n / 3) );
    }
}
