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
        int max = Arrays.stream(nums).min().orElse(0);
        int now = 0; // start with 0.


        for (int numi: nums) {
            now += numi;
            if (now < numi) now = numi;
            if (now > max) max = now; // record max;
        }

        return max;
    }
}
