package per.eicho.demo.leetcode.q101_200;

/**
 * <p>122. Best Time to Buy and Sell Stock II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/">
 *   122. Best Time to Buy and Sell Stock II</a>
 */
public final class Q122 {
    public int maxProfit(int[] prices) {
        // 1. 1 <= prices.length <= 3 * 10^4
        // 2. 0 <= prices[i] <= 10^4        
        if (prices == null) return 0;
        final int count = prices.length;

        int result = 0;
        for (int i = 0; i < count - 1; i++) {
            int j = i + 1;
            if (prices[j] < prices[i]) continue; // downtrend
            // uptrend: find last index j thant prices[j] >= prices[j-i].
            while (j < count && prices[j] >= prices[j - 1]) j++;
            j--;
            result += (prices[j] - prices[i]);
            i = j;
        }
        
        return result;      
    }
}
