package per.eicho.demo.leetcode.q1001_1100;

import java.util.PriorityQueue;

/**
 * <p>1029. Two City Scheduling 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/two-city-scheduling/">
 *   1029. Two City Scheduling</a>
 */
public final class Q1029 {
    public int twoCitySchedCost(int[][] costs) {
        // 1. 2 * n == costs.length
        // 2. 2 <= costs.length <= 100
        // 3. costs.length is even.
        // 4. 1 <= aCosti, bCosti <= 1000
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((cost1, cost2) -> {
            final int score1 = cost1[0] - cost1[1];
            final int score2 = cost2[0] - cost2[1];
            return Integer.compare(score1, score2);
        });

        for (int[] cost : costs) minHeap.add(cost);

        int result = 0;
        final int n = costs.length / 2;
        int p = 0;
        while (!minHeap.isEmpty()) result += minHeap.poll()[(p++) / n];

        return result;
    }
}
