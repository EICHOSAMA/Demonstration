package per.eicho.demo.leetcode.q1401_1500;

import java.util.Arrays;

/**
 * <p>1498. Number of Subsequences That Satisfy the Given Sum Condition 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/avoid-flood-in-the-city/">
 *   1498. Number of Subsequences That Satisfy the Given Sum Condition</a>
 */
public final class Q1498 {

    private static final int MODULO = 1_000_000_007;

    public int numSubseq(int[] nums, int target) {
        // 1. 1 <= nums.length <= 10^5
        // 2. 1 <= nums[i] <= 10^6
        // 3. 1 <= target <= 10^6
        final int n = nums.length;
        Arrays.sort(nums);
        int l = 0, r = n - 1; // [l, r]
        int numL;
        int numR = nums[r];

        int result = 0;
        while (l < n && (numL = nums[l]) * 2 <= target) {
            while (r > l && numL + numR > target) {
                // the sum of the minimum and maximum element on it 
                // is less or equal to target ( <= target)
                numR = nums[--r];
            }

            final int len = r - l + 1;
            if (len == 1) {
                result += 1;
            } else {
                result += pow(2L, (long)(len - 1));
                result %= MODULO;
            }

            l++;
        }

        return result;
    }

    private long pow(long a, long b) {
        if (b == 0L) return 1L;
        final long half = pow(a, b / 2) % MODULO;
        return (half * half * (b % 2L == 1L ? a : 1L)) % MODULO;
    }
}
