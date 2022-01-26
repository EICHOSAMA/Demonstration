package per.eicho.demo.leetcode.q201_300;

import java.util.Arrays;

/**
 * <p>215. Kth Largest Element in an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/kth-largest-element-in-an-array/">215. Kth Largest Element in an Array</a>
 */
public final class Q215 {
    public int findKthLargest(int[] nums, int k) {
        // 1. 1 <= k <= nums.length <= 10^4
        // 2. -10^4 <= nums[i] <= 10^4
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
