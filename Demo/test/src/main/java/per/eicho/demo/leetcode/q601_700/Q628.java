package per.eicho.demo.leetcode.q601_700;

import java.util.Arrays;

/**
 * <p>628. Maximum Product of Three Numbers 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-product-of-three-numbers/">628. Maximum Product of Three Numbers</a>
 */
public final class Q628 {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        
        // Last Index & First Index
        final int LI = nums.length - 1;
        final int FI = 0;
        return Math.max(nums[FI] * nums[FI + 1] * nums[FI + 2], 
               Math.max(nums[LI] * nums[LI - 1] * nums[LI - 2],
                        nums[FI] * nums[FI + 1] * nums[LI]));
    }
}
