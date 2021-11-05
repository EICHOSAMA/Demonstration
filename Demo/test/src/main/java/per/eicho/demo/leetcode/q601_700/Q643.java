package per.eicho.demo.leetcode.q601_700;

/**
 * <p>643. Maximum Average Subarray I 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-average-subarray-i/">643. Maximum Average Subarray I</a>
 */
public final class Q643 {
    public double findMaxAverage(int[] nums, int k) {
        /* 
          1. n == nums.length
          2. 1 <= k <= n <= 105
          3. -104 <= nums[i] <= 104
         */

        double max = 0;
        for (int i = 0; i < k; i++) {
            max += nums[i];
        }
        double current = max;

        for (int i = k; i < nums.length; i++) {
            current = current + nums[i] - nums[i - k]; // maintain the value of current variable.
            max = Math.max(max, current);
        }

        return max / k;
    }
}
