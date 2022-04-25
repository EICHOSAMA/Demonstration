package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1480. Running Sum of 1d Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/running-sum-of-1d-array/">
 *   1480. Running Sum of 1d Array</a>
 */
public final class Q1480 {
    public int[] runningSum(int[] nums) {
        // 1. 1 <= nums.length <= 1000
        // 2. -10^6 <= nums[i] <= 10^6        
        final int n = nums.length;
        final int[] runningSum = new int[n];
        
        int sum = 0;
        for (int i = 0; i < runningSum.length; i++) {
            sum += nums[i];
            runningSum[i] = sum;
        }

        return runningSum;
    }
}
