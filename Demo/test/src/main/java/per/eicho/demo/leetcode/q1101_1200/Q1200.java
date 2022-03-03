package per.eicho.demo.leetcode.q1101_1200;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>1200. Minimum Absolute Difference 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-absolute-difference/">
 *   1200. Minimum Absolute Difference</a>
 */
public final class Q1200 {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        // 1. 2 <= arr.length <= 10^5
        // 2. -10^6 <= arr[i] <= 10^6
        final int n = arr.length;        
        Arrays.sort(arr);
        final List<List<Integer>> result = new LinkedList<>();
        
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < n - 1 ; i++) {
            minDiff = Math.min(minDiff, arr[i + 1] - arr[i]);
        }

        for (int i = 0; i < n - 1; i++) {
            if (arr[i + 1] - arr[i] == minDiff) {
                result.add(Arrays.asList(arr[i], arr[i + 1]));
            }
        }

        return result;
    }
}
