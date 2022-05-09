package per.eicho.demo.leetcode.q1501_1600;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>1560. Most Visited Sector in a Circular Track 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/most-visited-sector-in-a-circular-track/">
 *   1560. Most Visited Sector in a Circular Track</a>
 */
public final class Q1560 {
    public List<Integer> mostVisited(int n, int[] rounds) {
        // 1. 2 <= n <= 100
        // 2. 1 <= m <= 100
        // 3. rounds.length == m + 1
        // 4. 1 <= rounds[i] <= n
        // 5. rounds[i] != rounds[i + 1] for 0 <= i < m        
        final int[] count = new int[n + 1];
        int p = rounds[0];
        count[p]++;
        for (int i = 1; i < rounds.length; i++) {
            final int round = rounds[i];

            while (p != round) {
                p++;
                if (p > n) p = 1;
                count[p]++;
            }
        }

        int max = 1;
        for (int i = 1; i <= n; i++) max = Math.max(max, count[i]);
        final List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) if (count[i] == max) result.add(i);
        return result;
    }
}
