package per.eicho.demo.leetcode.q401_500;

/**
 * <p>486. Predict the Winner 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/predict-the-winner/">
 *   486. Predict the Winner</a>
 */
public final class Q486 {
    public boolean PredictTheWinner(int[] nums) {
        // 1. 1 <= nums.length <= 20
        // 2. 0 <= nums[i] <= 10^7
        final int n = nums.length;
        final int[][] f = new int[n][n]; //[0, n)
        for (int i = 0; i < n; i++) f[i][i] = nums[i];
        for (int i = 0; i < n - 1; i++) f[i][i + 1] = Math.max(nums[i], nums[i + 1]);

        final int[] sums = new int[n];
        sums[0] = nums[0];
        for (int i = 1; i < n; i++) sums[i] = sums[i - 1] + nums[i];

        for (int len = 2; len <= n; len++) {
            int l = 0;
            int r = len - 1;
            
            while (r < n) {
                f[l][r] = Math.max((getSum(sums, l + 1, r) - f[l + 1][r]) + nums[l], 
                                   (getSum(sums, l, r - 1) - f[l][r - 1]) + nums[r]);
                l++; r++;
            }
        }
        return f[0][n - 1] * 2 >= getSum(sums, 0, n - 1);
    }

    private int getSum(int[] sum, int l, int r) {
        return sum[r] - (l == 0 ? 0 : sum[l - 1]);
    }
}
