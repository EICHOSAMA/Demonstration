package per.eicho.demo.leetcode.q2001_2100;

/**
 * <p>2006. Count Number of Pairs With Absolute Difference K 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/">2006. Count Number of Pairs With Absolute Difference K</a>
 */
public final class Q2006 {
    public int countKDifference(int[] nums, int k) {
        // 1. 1 <= nums.length <= 200
        // 2. 1 <= nums[i] <= 100
        // 3. 1 <= k <= 99
        int result = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            final int numI = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                final int numJ = nums[j];
                if (Math.abs(numI - numJ) == k) result++;
            }
        }

        return result;
    }
}
