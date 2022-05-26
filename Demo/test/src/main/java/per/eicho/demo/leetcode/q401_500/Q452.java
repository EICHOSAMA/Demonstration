package per.eicho.demo.leetcode.q401_500;

import java.util.Arrays;

/**
 * <p>452. Minimum Number of Arrows to Burst Balloons 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/">
 *   452. Minimum Number of Arrows to Burst Balloons</a>
 */
public final class Q452 {
    public int findMinArrowShots(int[][] points) {
        // 1. 1 <= points.length <= 10^5
        // 2. points[i].length == 2
        // 3. -2^31 <= x[start] < x[end] <= 2^31 - 1
        final int n = points.length;
        Arrays.sort(points, (p1, p2) -> {
            if (p1[0] != p2[0]) return Integer.compare(p1[0], p2[0]);
            return Integer.compare(p1[1], p2[1]);
        });

        int result = 0;
        int p = 0;
        while (p < n) {
            int h = points[p][1];
            while (p + 1 < n && h >= points[p + 1][0]) {
                p++;
                h = Math.min(h, points[p][1]);
            }
            result++;
            p++;
        }
        
        return result;
    }
}
