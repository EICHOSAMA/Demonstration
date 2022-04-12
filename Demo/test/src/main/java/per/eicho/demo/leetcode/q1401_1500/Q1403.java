package per.eicho.demo.leetcode.q1401_1500;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>1403. Minimum Subsequence in Non-Increasing Order 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-subsequence-in-non-increasing-order/">
 *   1403. Minimum Subsequence in Non-Increasing Order</a>
 */
public final class Q1403 {
    public List<Integer> minSubsequence(int[] nums) {
        // 1. 1 <= nums.length <= 500
        // 2. 1 <= nums[i] <= 100        
        final List<Integer> result = new LinkedList<>();

        Arrays.sort(nums);
        final int n = nums.length;

        int sumL = 0;
        for (int num : nums) sumL += num;

        int sumR = 0;
        for (int i = n - 1; i >= 0; i--) {
            final int num = nums[i];

            sumR += num;
            sumL -= num;

            result.add(num);

            if (sumR > sumL) break;
        }
        return result;
    }
}
