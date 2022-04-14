package per.eicho.demo.leetcode.q1401_1500;

import java.util.Arrays;

/**
 * <p>1464. Maximum Product of Two Elements in an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/">
 *   1464. Maximum Product of Two Elements in an Array</a>
 */
public final class Q1464 {
    public int maxProduct(int[] nums) {
        // 1. 2 <= nums.length <= 500
        // 2. 1 <= nums[i] <= 10^3
        final int[] max = new int[2];
        max[0] = nums[0];
        max[1] = nums[1];
        Arrays.sort(max); // ascending

        final int n = nums.length;
        for (int i = 2; i < n; i++) {
            final int num = nums[i];

            if (num >= max[1]) {
                max[0] = max[1];
                max[1] = num;
                continue;
            }

            if (num > max[0]) max[0] = num;
        }

        return (max[0] - 1) * (max[1] - 1);
    }
}
