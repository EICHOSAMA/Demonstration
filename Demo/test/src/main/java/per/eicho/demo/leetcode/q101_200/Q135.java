package per.eicho.demo.leetcode.q101_200;

import java.util.Arrays;

/**
 * <p>135. Candy 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/candy/">135. Candy</a>
 */
public final class Q135 {
    public int candy(int[] ratings) {
        // 1. n == ratings.length
        // 2. 1 <= n <= 2 * 10^4
        // 3. 0 <= ratings[i] <= 2 * 10^4
        final int n = ratings.length;
        if (n == 1) return 1;

        final int[] candies = new int[n];
        Arrays.fill(candies, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        int result = candies[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            result += candies[i];
        }
        return result;
    }
}
