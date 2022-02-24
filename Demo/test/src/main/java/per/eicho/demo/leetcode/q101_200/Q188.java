package per.eicho.demo.leetcode.q101_200;

/**
 * <p>188. Best Time to Buy and Sell Stock IV 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/">
 *   188. Best Time to Buy and Sell Stock IV</a>
 */
public final class Q188 {
    public int maxProfit(int k, int[] prices) {
        // 1. 0 <= k <= 100
        // 2. 0 <= prices.length <= 1000
        // 3. 0 <= prices[i] <= 1000        
        final int n = prices.length;
        final int[][] f = new int[k + 1][n + 1];

        for (int i = k; i > 0; i--) {
            for (int j = n; j > 0; j--) {
                f[i][j - 1] = Math.max(f[i][j - 1], f[i][j]);
                f[i - 1][j] = Math.max(f[i - 1][j], f[i][j]);

                for (int l = j - 1; l > 0; l--) {
                    final int profix = getProfix(prices, l, j);
                    f[i - 1][l - 1] = Math.max(f[i - 1][l - 1], f[i][j] + profix);
                }
            }
        }

        for (int j = n; j > 0; j--) {
            f[0][j - 1] = Math.max(f[0][j - 1], f[0][j]);
        }

        return f[0][0];
    }

    private int getProfix(int[] prices, int start, int end) {
        return prices[end - 1] - prices[start - 1];
    }
}
