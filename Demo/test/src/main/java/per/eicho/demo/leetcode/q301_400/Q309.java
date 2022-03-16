package per.eicho.demo.leetcode.q301_400;

/**
 * <p>309. Best Time to Buy and Sell Stock with Cooldown 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/">
 *   309. Best Time to Buy and Sell Stock with Cooldown</a>
 */
public final class Q309 {
    public int maxProfit(int[] prices) {
        // 1. 1 <= prices.length <= 5000
        // 2. 0 <= prices[i] <= 1000
        final int n = prices.length;        
        final int[][] f = new int[n + 2][2];
        for (int i = 0; i < f.length; i++) { f[i][1] = Integer.MIN_VALUE; }

        for (int i = 0; i < n; i++) {
            final int price = prices[i];
            f[i + 1][1] = Math.max(f[i + 1][1], f[i][1]); // do nothing
            f[i + 1][1] = Math.max(f[i + 1][1], f[i][0] - price); // buy

            f[i + 1][0] = Math.max(f[i + 1][0], f[i][0]); // do nothing
            f[i + 2][0] = Math.max(f[i + 2][0], f[i][1] + price); // sell + cool down (2days)
        }
        return Math.max(f[n][0], f[n + 1][0]);
    }
}
