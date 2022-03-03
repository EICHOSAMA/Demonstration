package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1266. Minimum Time Visiting All Points 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-time-visiting-all-points/">
 *   1266. Minimum Time Visiting All Points</a>
 */
public final class Q1266 {
    public int minTimeToVisitAllPoints(int[][] points) {
        // 1. points.length == n
        // 2. 1 <= n <= 100
        // 3. points[i].length == 2
        // 4. -1000 <= points[i][0], points[i][1] <= 1000
        if (points.length == 1) return 0;

        int result = 0;
        int[] cursor = points[0];
        for (int i = 1; i < points.length; i++) {
            final int[] next = points[i];

            final int xDiff = Math.abs(next[0] - cursor[0]);
            final int yDiff = Math.abs(next[1] - cursor[1]);

            result = result + Math.max(xDiff, yDiff);

            // move to next.
            cursor = next;
        }

        return result;
    }
}
