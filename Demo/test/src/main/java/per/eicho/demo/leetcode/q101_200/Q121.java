package per.eicho.demo.leetcode.q101_200;

/**
 * <p>121. Best Time to Buy and Sell Stock 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/">121. Best Time to Buy and Sell Stock</a>
 */
public final class Q121 {
    public int maxProfit(int[] prices) {
        // 1. 1 <= prices.length <= 10^5
        // 2. 0 <= prices[i] <= 10^4        
        if (prices.length == 0) return 0;

        int maxProfit = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice)
                minPrice = prices[i];
            else if (prices[i] - minPrice > maxProfit)
                maxProfit = prices[i] - minPrice;
        }
        return maxProfit;
    }
}
