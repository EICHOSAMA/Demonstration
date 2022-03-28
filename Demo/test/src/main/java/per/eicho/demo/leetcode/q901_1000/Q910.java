package per.eicho.demo.leetcode.q901_1000;

import java.util.Arrays;

/**
 * <p>910. Smallest Range II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/smallest-range-ii/">910. Smallest Range II</a>
 */
public final class Q910 {
    public int smallestRangeII(int[] nums, int k) {
        // 1. 1 <= nums.length <= 10^4
        // 2. 0 <= nums[i] <= 10^4
        // 3. 0 <= k <= 10^4
        final int n = nums.length;
        Arrays.sort(nums);
        int ans = nums[n - 1] - nums[0];

        for (int i = 0; i < n - 1; i++) {
            // 枚举 +-区间。
            final int upBiggest = nums[i];
            final int downSmallest = nums[i + 1];
            // [0, i] up 区间，[i + 1, n -1] down 区间
            final int newHigh = Math.max(nums[n - 1] - k, upBiggest + k);
            final int newLow = Math.min(nums[0] + k, downSmallest - k);
            ans = Math.min(ans, newHigh - newLow);
        }
        return ans;
    }
}
