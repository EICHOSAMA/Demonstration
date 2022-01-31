package per.eicho.demo.leetcode.q2101_2200;

import java.util.Arrays;

/**
 * <p>2154. Keep Multiplying Found Values by Two 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/keep-multiplying-found-values-by-two/">2154. Keep Multiplying Found Values by Two</a>
 */
public final class Q2154 {
    public int findFinalValue(int[] nums, int original) {
        // 1. 1 <= nums.length <= 1000
        // 2. 1 <= nums[i], original <= 1000
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == original) original *= 2;
        }
        return original;
    }
}
