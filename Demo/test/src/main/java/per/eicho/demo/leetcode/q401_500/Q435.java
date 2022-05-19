package per.eicho.demo.leetcode.q401_500;

import java.util.Arrays;

/**
 * <p>435. Non-overlapping Intervals 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/non-overlapping-intervals/">
 *   435. Non-overlapping Intervals</a>
 */
public final class Q435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        // 1. 1 <= intervals.length <= 10^5
        // 2. intervals[i].length == 2
        // 3. -5 * 10^4 <= starti < endi <= 5 * 10^4        
        final int n = intervals.length;
        Arrays.sort(intervals, (i1, i2) -> {
            if (i1[0] != i2[0]) return Integer.compare(i1[0], i2[0]); // ascending.
            return Integer.compare(i2[1], i1[1]); // descending.
        });

        int count = 0;
        int p = 0;
        int end = -50_000;
        while (p < n) {
            int start = intervals[p][0];
            while (p + 1 < n && start == intervals[p + 1][0]) {
                p++;
                count++;
            }

            final int newEnd = intervals[p][1];

            if (start >= end) {
                end = newEnd;
            } else {
                count++;
                end = Math.min(end, newEnd);
            }
        
            p++;
        }
        return count;
    }
}
