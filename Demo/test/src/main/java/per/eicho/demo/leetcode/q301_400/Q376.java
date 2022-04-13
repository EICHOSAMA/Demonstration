package per.eicho.demo.leetcode.q301_400;

/**
 * <p>376. Wiggle Subsequence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/wiggle-subsequence/">
 *   376. Wiggle Subsequence</a>
 */
public final class Q376 {
    public int wiggleMaxLength(int[] nums) {
        // 1. 1 <= nums.length <= 1000
        // 2. 0 <= nums[i] <= 1000        
        final int n = nums.length;
        final int[][] f = new int[n][2];
        for (int i = 0; i < n; i++) f[i][0] = f[i][1] = 1;

        int result = 1;
        for (int i = 0; i < n; i++) {
            final int num1 = nums[i];
            for (int j = i + 1; j < n; j++) {
                final int num2 = nums[j];
                if (num2 == num1) continue;

                if (num2 > num1) {
                    f[j][0] = Math.max(f[j][0], f[i][1] + 1);
                } else { // num2 < num1
                    f[j][1] = Math.max(f[j][1], f[i][0] + 1);
                }
            }
            result = Math.max(result, Math.max(f[i][0], f[i][1]));
        }
        return result;
    }
}
