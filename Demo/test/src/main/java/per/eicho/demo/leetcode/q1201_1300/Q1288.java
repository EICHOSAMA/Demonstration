package per.eicho.demo.leetcode.q1201_1300;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>1288. Remove Covered Intervals 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/remove-covered-intervals/">
 *   1288. Remove Covered Intervals</a>
 */
public final class Q1288 {
    public int removeCoveredIntervals(int[][] intervals) {
        // 1. 1 <= intervals.length <= 1000
        // 2. intervals[i].length == 2
        // 3. 0 <= li < ri <= 10^5
        // 4. All the given intervals are unique.
        final int n = intervals.length;
        Arrays.sort(intervals, (i1, i2) -> {
            if (i1[0] != i2[0]) return Integer.compare(i1[0], i2[0]); // ascending
            return Integer.compare(i2[1], i1[1]); // descending.
        });
        final Deque<int[]> result = new LinkedList<>();
        result.add(intervals[0]);

        for (int i = 1; i < n; i++) {
            final int[] interval = intervals[i];   
            if (interval[1] <= result.peekLast()[1]) continue;
            result.addLast(interval);
        }
        return result.size();
    }
}
