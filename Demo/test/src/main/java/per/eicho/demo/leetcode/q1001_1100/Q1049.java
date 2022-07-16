package per.eicho.demo.leetcode.q1001_1100;

/**
 * <p>1049. Last Stone Weight II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/last-stone-weight-ii/">
 *   1049. Last Stone Weight II</a>
 */
public final class Q1049 {
    public int lastStoneWeightII(int[] stones) {
        // 1. 1 <= stones.length <= 30
        // 2. 1 <= stones[i] <= 100
        int sum = 0;
        for (int stone : stones) sum += stone;

        final int n = stones.length;
        int m = sum / 2;

        final boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        
        for (int i = 0; i < n; i++) {
            final int stone = stones[i];

            for (int j = 0; j <= m; j++) {
                if (j < stone) {
                    f[i + 1][j] = f[i][j];
                } else {
                    f[i + 1][j] = f[i][j] || f[i][j - stone];
                }
            }
        }

        for (int j = m; j >= 0; j--) if (f[n][j]) return sum - 2 * j;
        return 0;
    }
}
