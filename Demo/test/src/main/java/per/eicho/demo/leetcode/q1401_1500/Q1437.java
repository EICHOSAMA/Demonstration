package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1437. Check If All 1's Are at Least Length K Places Away 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/">
 *   1437. Check If All 1's Are at Least Length K Places Away</a>
 */
public final class Q1437 {
    public boolean kLengthApart(int[] nums, int k) {
        // 1. 1 <= nums.length <= 10^5
        // 2. 0 <= k <= nums.length
        // 3. nums[i] is 0 or 1
        final int n = nums.length;

        int prev1 = -1_000_000;
        for (int i = 0; i < n; i++) {
            final int num = nums[i];

            if (num == 0) continue;

            if (i - prev1 - 1 < k) return false;
            prev1 = i;
        }
        return true;
    }
}
