package per.eicho.demo.leetcode.q401_500;

import java.util.Arrays;

/**
 * <p>436. Find Right Interval 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-right-interval/">
 *   436. Find Right Interval</a>
 */
public final class Q436 {
    public int[] findRightInterval(int[][] intervals) {
        // 1. 1 <= intervals.length <= 2 * 10^4
        // 2. intervals[i].length == 2
        // 3. -10^6 <= start[i] <= end[i] <= 10^6
        // 4. The start point of each interval is unique.        
        final int n = intervals.length;
        final int[] result = new int[n];
        final int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            ends[i] = intervals[i][1];
            intervals[i][1] = i;
        }

        // Note: The start point of each interval is unique.
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        for (int i = 0; i < n; i++) {
            final int end = ends[i];
            if (end > intervals[n - 1][0]) {
                result[i] = -1;
            } else {
                result[i] = binarySearch(intervals, 0, n - 1, end);
            }
        }

        return result;
    }

    private int binarySearch(int[][] intervals, int l, int r, int target) {
        if (l == r) return intervals[l][1];
        
        int mid = (l + r) / 2;
        int midStart = intervals[mid][0];

        if (midStart < target) return binarySearch(intervals, mid + 1, r, target);
        return binarySearch(intervals, l, mid, target);
    }
}
