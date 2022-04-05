package per.eicho.demo.leetcode.q201_300;

import java.util.Arrays;

/**
 * <p>279. Perfect Squares 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/perfect-squares/">
 *   279. Perfect Squares</a>
 */
public final class Q279 {

    int[] f;

    public int numSquares(int n) {
        // 1 <= n <= 10^4
        f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 1, step = 1; step < n; i++, step = i * i) f[step] = 1;
        f[0] = 0;
        return search(n);
    }

    private int search(int n) {
        if (f[n] != 0) return f[n];

        int i = 1, step = 1;
        while (n - step > 0) {
            f[n] = Math.min(f[n], search(n - step) + 1);
            i++;
            step = i * i;
        }
        return f[n];
    }
}
