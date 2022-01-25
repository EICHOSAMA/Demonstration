package per.eicho.demo.leetcode.q2101_2200;

import java.util.Arrays;

/**
 * <p>2144. Minimum Cost of Buying Candies With Discount 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-cost-of-buying-candies-with-discount/">
 *   2144. Minimum Cost of Buying Candies With Discount</a>
 */
public final class Q2144 {
    public int minimumCost(int[] cost) {
        // 1. 1 <= cost.length <= 100
        // 2. 1 <= cost[i] <= 100
        Arrays.sort(cost);
        int result = 0;
        for (int i = cost.length - 1; i >= 0; i--) {
            result += cost[i];
            if (--i < 0) continue;
            result += cost[i];
            i--;
        }

        return result;
    }
}
