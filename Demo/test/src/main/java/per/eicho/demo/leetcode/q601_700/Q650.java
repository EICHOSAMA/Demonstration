package per.eicho.demo.leetcode.q601_700;

import java.util.Arrays;

/**
 * <p>650. 2 Keys Keyboard 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/2-keys-keyboard/">
 *   650. 2 Keys Keyboard</a>
 */
public final class Q650 {
    public int minSteps(int n) {
        // 1. 1 <= n <= 1000
        final int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[1] = 0;

        for (int i = 1; i <= n; i++) {
            if (f[i] == Integer.MAX_VALUE) continue;
            int op = 1; // copy
            for (int j = 2 * i; j <= n; j += i) {
                op++;
                f[j] = Math.min(f[j], f[i] + op);
            }
        }
        return f[n];
    }
}
