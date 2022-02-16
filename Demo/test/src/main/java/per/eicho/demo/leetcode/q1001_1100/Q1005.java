package per.eicho.demo.leetcode.q1001_1100;

import java.util.Arrays;

/**
 * <p>1005. Maximize Sum Of Array After K Negations 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/">1005. Maximize Sum Of Array After K Negations</a>
 */
public final class Q1005 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 1. 1 <= nums.length <= 10^4
        // 2. -100 <= nums[i] <= 100
        // 3. 1 <= k <= 10^4
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 || k == 0) break;
            
            nums[i] *= -1;
            k--;
        }

        if (k > 0) k %= 2;
        if (k > 0) {
            Arrays.sort(nums);
            nums[0] *= -1;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        return sum;
    }
}
