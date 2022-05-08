package per.eicho.demo.leetcode.q301_400;

import java.util.Arrays;

/**
 * <p>375. Guess Number Higher or Lower II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/guess-number-higher-or-lower-ii/">
 *   375. Guess Number Higher or Lower II</a>
 */
public final class Q375 {
    public int getMoneyAmount(int n) {
        // 1. 1 <= n <= 200
        if (n == 1) return 1;
        final int[][] f = new int[n + 1][n + 1];
        for (int[] row : f) Arrays.fill(row, -1);
        for (int i = 0; i <= n; i++) {
            f[i][i] = 0;
            if (i + 1 <= n) f[i][i + 1] = i;
            if (i + 2 <= n) f[i][i + 2] = i + 1;
        }

        int money = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            money = Math.min(money, i + Math.max(search(f, 1, i - 1), search(f, i + 1, n)));
        }

        return money;
    }

    private int search(int[][] f, int l, int r) {
        if (l > r) return 0;
        if (f[l][r] != -1) return f[l][r];

        int money = Integer.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            money = Math.min(money, i + Math.max(search(f, l, i - 1),search(f, i + 1, r)));
        }

        return f[l][r] = money;
    }

    public static void main(String[] args) {
        Q375 q375 = new Q375();
        System.out.println(q375.getMoneyAmount(10));
    }
}
