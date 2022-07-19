package per.eicho.demo.leetcode.q1101_1200;

/**
 * <p>1186. Maximum Subarray Sum with One Deletion 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/">
 *   1186. Maximum Subarray Sum with One Deletion</a>
 */
public final class Q1186 {
    public int maximumSum(int[] arr) {
        // 1. 1 <= arr.length <= 10^5
        // 2. -10^4 <= arr[i] <= 10^4
        final int n = arr.length;
        final int[][] f = new int[n][2];

        f[0][0] = -2_000_000_000;
        f[0][1] = arr[0];

        int result = Math.max(f[0][0], f[0][1]);
        for (int i = 1; i < f.length; i++) {
            final int num = arr[i];
            f[i][0] = Math.max(f[i - 1][0] + num, f[i - 1][1]);
            f[i][1] = Math.max(f[i - 1][1] + num, num);
            result = Math.max(result, Math.max(f[i][0], f[i][1]));
        }

        return result;
    }
}
