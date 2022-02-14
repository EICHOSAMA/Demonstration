package per.eicho.demo.leetcode.q101_200;

/**
 * <p>123. Best Time to Buy and Sell Stock III 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/">123. Best Time to Buy and Sell Stock III</a>
 */
public final class Q123 {
    public int maxProfit(int[] prices) {
        // 1 <= prices.length <= 10^5
        // 0 <= prices[i] <= 10^5
        final int n = prices.length;
        final int[][] f = new int[n][4];
        
        int result = 0;
        int f0 = -100_000_000, f1 = f0, f2 = f0;
        for (int i = 0; i < n; i++) {
            final int price = prices[i];
            f[i][0] = -price; // buy

            f[i][1] = f0 + price; // sell
            f[i][2] = f1 - price; // buy
            f[i][3] = f2 + price; // sell

            f0 = Math.max(f0, f[i][0]);
            f1 = Math.max(f1, f[i][1]);
            f2 = Math.max(f2, f[i][2]);

            result = Math.max(result, Math.max(f[i][1], f[i][3]));
        }

        return result;
    }
}
