package per.eicho.demo.leetcode.q401_500;

import java.util.Arrays;

import per.eicho.demo.algorithm.quickselect.QuickSelectSample;

/**
 * <p>462. Minimum Moves to Equal Array Elements II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/">
 *   462. Minimum Moves to Equal Array Elements II</a>
 * @see {@link QuickSelectSample}
 */
public final class Q462 {
    public int minMoves2(int[] nums) {
        // 1. n == nums.length
        // 2. 1 <= nums.length <= 10^5
        // 3. -10^9 <= nums[i] <= 10^9     
        final int n = nums.length;
        Arrays.sort(nums);

        final int target = nums[n / 2];
        int result = 0;
        for (int num : nums) result += Math.abs(num - target);
        return result;
    }
}
