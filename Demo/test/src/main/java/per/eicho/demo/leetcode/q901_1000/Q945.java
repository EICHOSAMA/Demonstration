package per.eicho.demo.leetcode.q901_1000;

import java.util.Arrays;

/**
 * <p>945. Minimum Increment to Make Array Unique 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-increment-to-make-array-unique/">
 *   945. Minimum Increment to Make Array Unique</a>
 */
public final class Q945 {
    public int minIncrementForUnique(int[] nums) {
        // 1. 1 <= nums.length <= 10^5
        // 2. 0 <= nums[i] <= 10^5
        final int n = nums.length;
        Arrays.sort(nums);
        int result = 0;
        int minUsableNum = nums[0] + 1;
        for (int i = 1; i < n; i++) {
            final int num = nums[i];
            if (num >= minUsableNum) {
                minUsableNum = num + 1;
            } else {
                // num < minUsableNum
                result += (minUsableNum - num);
                minUsableNum++;
            }
        }
        return result;
    }
}
