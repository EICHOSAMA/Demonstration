package per.eicho.demo.leetcode.q001_100;

/**
 * <p>7. Reverse Integer 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reverse-integer/">7. Reverse Integer</a>
 */
public final class Q70 {
    public int climbStairs(int n) {
        // 1 <= n <= 45
        final int[] f = new int[n + 1];

        f[0] = 1;

        for (int i = 0; i < n - 1; i++) {
            final int fi = f[i];

            f[i + 1] += fi;
            f[i + 2] += fi;
        }
        f[n] += f[n - 1];
        return f[n];
    }
}
