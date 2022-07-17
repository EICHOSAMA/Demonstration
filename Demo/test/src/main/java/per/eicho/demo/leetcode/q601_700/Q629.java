package per.eicho.demo.leetcode.q601_700;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * <p>629. K Inverse Pairs Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/k-inverse-pairs-array/">
 *   629. K Inverse Pairs Array</a>
 */
public final class Q629 {

    int[][] memo;
    private static final int MODULO = 1_000_000_000 + 7;

    public int kInversePairs(int n, int k) {
        // 1. 1 <= n <= 1000
        // 2. 0 <= k <= 1000
        if (k == 0) return 1;
        memo = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            final int[] row = memo[i];
            Arrays.fill(row, -1);
            row[0] = 1;
            row[1] = i - 1;
        }
        
        return dp(n, k);
    }

    private int dp(int n, int k) {
        if (memo[n][k] != -1) return memo[n][k];

        long result = 0;
        for (int p = n - 1, cnt = 0; p >= 0; p--, cnt++) {
            if (k - cnt < 0) break;
            result += dp(n - 1, k - cnt);
        }
        return memo[n][k] = (int)(result % MODULO);
    }

    public static void main(String[] args) {
        Q629 q629 = new Q629();
        System.out.println(LocalDateTime.now());
        System.out.println(q629.kInversePairs(1000, 1000));
        System.out.println(LocalDateTime.now());
    }
}
