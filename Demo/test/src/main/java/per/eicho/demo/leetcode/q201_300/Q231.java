package per.eicho.demo.leetcode.q201_300;

/**
 * <p>231. Power of Two 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/power-of-two/">
 *   231. Power of Two</a>
 */
public final class Q231 {
    public boolean isPowerOfTwo(int n) {
        // 1. -2^31 <= n <= 2^31 - 1
        if (n <= 0) return false;
        if (n < 3) return true;
        return n % 2 == 0 ? isPowerOfTwo(n/2) : false;
    }
}
