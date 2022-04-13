package per.eicho.demo.leetcode.q301_400;

/**
 * <p>376. Wiggle Subsequence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/wiggle-subsequence/">
 *   376. Wiggle Subsequence</a>
 */
public final class Q376 {

    private static final int UP = 0;
    private static final int DOWN = 1;

    public int wiggleMaxLength(int[] nums) {
        // 1. 1 <= nums.length <= 1000
        // 2. 0 <= nums[i] <= 1000        
        final int n = nums.length;
        final int[][] f = new int[n][2];
        f[0][UP] = f[0][DOWN] = 1;

        for (int i = 1; i < n; i++) {
            final int num1 = nums[i - 1];
            final int num2 = nums[i];

            if (num1 == num2) {
                f[i][UP] = f[i - 1][UP];
                f[i][DOWN] = f[i - 1][DOWN];
                continue;
            }

            if (num2 > num1) { // ↑
                f[i][UP] = Math.max(f[i - 1][UP], f[i - 1][DOWN] + 1);
                f[i][DOWN] = f[i - 1][DOWN];
                continue;
            }
            
            // num2 < num1, ↓
            f[i][UP] = f[i - 1][UP];
            f[i][DOWN] = Math.max(f[i - 1][UP] + 1, f[i - 1][DOWN]);
        }
        return Math.max(f[n - 1][UP], f[n - 1][DOWN]);
    }
}
