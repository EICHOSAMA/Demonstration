package per.eicho.demo.leetcode.q501_600;

import per.eicho.demo.leetcode.q101_200.Q136;

/**
 * <p>540. Single Element in a Sorted Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/single-element-in-a-sorted-array/">
 *   540. Single Element in a Sorted Array</a>
 * @see {@link Q136 Same Question}
 */
public final class Q540 {
    public int singleNonDuplicate(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }
}
