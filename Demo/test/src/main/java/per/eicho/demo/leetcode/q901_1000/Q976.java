package per.eicho.demo.leetcode.q901_1000;

import java.util.Arrays;

/**
 * <p>976. Largest Perimeter Triangle 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/largest-perimeter-triangle/">976. Largest Perimeter Triangle</a>
 */
public final class Q976 {
    public int largestPerimeter(int[] nums) {
        // 1. 3 <= nums.length <= 10^4
        // 2. 1 <= nums[i] <= 10^6        
        final int n = nums.length;
        Arrays.sort(nums);

        for (int i = n - 1; i >= 2; i--) {
            final int a = nums[i];
            final int b = nums[i - 1];
            final int c = nums[i - 2];
            if (b + c > a) return a + b + c;
        }
        return 0;
    }
}
