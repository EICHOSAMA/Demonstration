package per.eicho.demo.leetcode.q701_800;

/**
 * <p>714. Best Time to Buy and Sell Stock with Transaction Fee 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/">
 *   714. Best Time to Buy and Sell Stock with Transaction Fee</a>
 */
public final class Q714 {
    public int maxProfit(int[] prices, int fee) {
        // 1. 1 <= prices.length <= 5 * 10^4
        // 2. 1 <= prices[i] < 5 * 10^4
        // 3. 0 <= fee < 5 * 10^4
        final int n = prices.length;
        final int[][] f = new int[n][2];
        for (int i = 0; i < n; i++) f[i][1] = -1_250_000_000;
        for (int i = 1; i < n; i++) {
            f[i][0] = Math.max(f[i][0], Math.max(f[i - 1][0], f[i - 1][1] + prices[i - 1] - fee));
            f[i][1] = Math.max(f[i][1], Math.max(f[i - 1][1], f[i - 1][0] - prices[i - 1]));
        }
        return Math.max(f[n - 1][0], f[n - 1][1] + prices[n - 1] - fee);
    }

    public static void main(String[] args) {
        Q714 q714 = new Q714();
        System.out.println(q714.maxProfit(new int[]{1,3,2,8,4,9}, 2)); // 8
        System.out.println(q714.maxProfit(new int[]{1,4,6,2,8,3,10,14}, 3)); // 13
    }
}
