package per.eicho.demo.leetcode.q601_700;

/**
 * <p>673. Number of Longest Increasing Subsequence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-longest-increasing-subsequence/">
 *   673. Number of Longest Increasing Subsequence</a>
 */
public final class Q673 {
    public int findNumberOfLIS(int[] nums) {
        // 1. 1 <= nums.length <= 2000
        // 2. -10^6 <= nums[i] <= 10^6        
        final int n = nums.length; 
        int maxLen = 0;
        int count = 0;
        final int[] f = new int[n];
        final int[] cnt = new int[n];
        for (int i = 0; i < n; ++i) {
            final int num = nums[i];
            f[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (num > nums[j]) {
                    if (f[j] + 1 > f[i]) {
                        f[i] = f[j] + 1;
                        cnt[i] = cnt[j]; 
                    } else if (f[j] + 1 == f[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }

            if (f[i] > maxLen) {
                maxLen = f[i];
                count = cnt[i];
            } else if (f[i] == maxLen) {
                count += cnt[i];
            }
        }
        return count;
    }
}
