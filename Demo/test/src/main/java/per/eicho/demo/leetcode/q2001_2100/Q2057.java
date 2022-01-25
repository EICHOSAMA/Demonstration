package per.eicho.demo.leetcode.q2001_2100;

/**
 * <p>2057. Smallest Index With Equal Value 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/smallest-index-with-equal-value/">
 *   2057. Smallest Index With Equal Value</a>
 */
public final class Q2057 {
    public int smallestEqual(int[] nums) {
        // 1. 1 <= nums.length <= 100
        // 2. 0 <= nums[i] <= 9
        for (int i = 0; i < nums.length; i++) {
            if (i % 10 == nums[i]) return i;
        }
        return -1;
    }
}
