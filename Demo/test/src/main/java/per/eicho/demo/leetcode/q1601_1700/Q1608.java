package per.eicho.demo.leetcode.q1601_1700;

import java.util.Arrays;

/**
 * <p>1608. Special Array With X Elements Greater Than or Equal X 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/">
 *   1608. Special Array With X Elements Greater Than or Equal X</a>
 */
public final class Q1608 {
    public int specialArray(int[] nums) {
        // 1. 1 <= nums.length <= 100
        // 2. 0 <= nums[i] <= 1000 
        Arrays.sort(nums);
        final int n = nums.length;
        for (int i = n - 1; i >= 1; i--) {
            if (nums[i - 1] == nums[i]) continue;

            // nums is considered special if there exists a number x 
            // such that there are exactly x numbers in nums that are greater than or equal to x.
            int x = n - i;
            if (nums[i - 1] < x && x <= nums[i]) return x;
        }
        return (n <= nums[0]) ? n : -1;
    }
}
