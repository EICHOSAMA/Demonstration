package per.eicho.demo.leetcode.q1401_1500;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>1431. Kids With the Greatest Number of Candies 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/">
 *   1431. Kids With the Greatest Number of Candies</a>
 */
public final class Q1431 {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        // 1. n == candies.length
        // 2. 2 <= n <= 100
        // 3. 1 <= candies[i] <= 100
        // 4. 1 <= extraCandies <= 50 
        final int n = candies.length;
        int max = 0;
        for (int candy : candies) max = Math.max(max, candy);

        final List<Boolean> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) result.add(candies[i] + extraCandies >= max);
        return result;
    }
}
