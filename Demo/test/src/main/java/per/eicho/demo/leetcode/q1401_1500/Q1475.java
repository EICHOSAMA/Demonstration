package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1475. Final Prices With a Special Discount in a Shop 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/">
 *   1475. Final Prices With a Special Discount in a Shop</a>
 */
public final class Q1475 {
    public int[] finalPrices(int[] prices) {
        // 1. 1 <= prices.length <= 500
        // 2. 1 <= prices[i] <= 10^3
        final int n = prices.length;
        final int[] finalPrices = new int[n];

        for (int i = 0; i < n; i++) {
            int price = prices[i];

            for (int j = i + 1; j < n; j++) {
                if (prices[j] <= price) {
                    price -= prices[j];
                    break;
                }
            }

            finalPrices[i] = price;
        }

        return finalPrices;
    }
}
