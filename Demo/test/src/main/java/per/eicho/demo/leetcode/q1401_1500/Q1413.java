package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1413. Minimum Value to Get Positive Step by Step Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-value-to-get-positive-step-by-step-sum/">
 *   1413. Minimum Value to Get Positive Step by Step Sum</a>
 */
public final class Q1413 {
    public int minStartValue(int[] nums) {
        // 1. 1 <= nums.length <= 100
        // 2. -100 <= nums[i] <= 100        

        for (int i = 1; i < nums.length; i++) nums[i] += nums[i - 1];

        int min = nums[0];
        for (int i = 1; i < nums.length; i++) min = Math.min(min, nums[i]);

        return min >= 1 ? 0 : 1 - min;
    }
}
