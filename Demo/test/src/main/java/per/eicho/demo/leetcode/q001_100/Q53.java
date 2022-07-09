package per.eicho.demo.leetcode.q001_100;

import java.util.Arrays;

/**
 * <p>53. Maximum Subarray 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-subarray/">53. Maximum Subarray</a>
 */
public final class Q53 {
    public int maxSubArray(int[] nums) {
        // 1. 1 <= nums.length <= 10^5
        // 2. -10^4 <= nums[i] <= 10^4        
        final int n = nums.length;
        int result = nums[0];
        int f = nums[0];
        for (int i = 1; i < n; i++) {
            final int num = nums[i];
            f = Math.max(f + num, num);
            result = Math.max(result, f);
        }

        return result;
    }
}
