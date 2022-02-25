package per.eicho.demo.leetcode.q1101_1200;

/**
 * <p>1137. N-th Tribonacci Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/n-th-tribonacci-number/">1137. N-th Tribonacci Number</a>
 */
public final class Q1137 {
    public int tribonacci(int n) {
        // 1. 0 <= n <= 37
        // 2. The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.        
        final int[] f = new int[38];
        f[0] = 0;
        f[1] = 1;
        f[2] = 1;
        for (int i = 3; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2] + f[i - 3];
        }
        return f[n];
    }
}
