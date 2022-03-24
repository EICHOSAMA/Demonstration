package per.eicho.demo.leetcode.q201_300;

/**
 * <p>268. Missing Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/missing-number/">268. Missing Number</a>
 */
public final class Q268 {
    public int missingNumber(int[] nums) {
        // n == nums.length
        // 1 <= n <= 10^4
        // 0 <= nums[i] <= n
        // All the numbers of nums are unique.
        final int n = nums.length; // missing one. 0 , 1, 3 , length = 3, n = 3

        int result = 0;
        int i = 0;
        for (; i < n; i++) {
            result ^= i;
            result ^= nums[i];
        }
        result ^= i;
        return result;
    }
}
