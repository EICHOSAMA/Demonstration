package per.eicho.demo.leetcode.q301_400;

import java.util.Arrays;

/**
 * <p>312. Burst Balloons 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/burst-balloons/">
 *   312. Burst Balloons</a>
 */
public final class Q312 {
    
    static final int UNPROCESSED = -1; 
    int[][] memo;
    int n;
    int[] nums;

    public int maxCoins(int[] nums) {
        // 1. n == nums.length
        // 2. 1 <= n <= 300
        // 3. 0 <= nums[i] <= 100
        n = nums.length;
        this.nums = new int[n + 2]; // [0, n + 1]
        System.arraycopy(nums, 0, this.nums, 1, n);
        this.nums[0] = this.nums[n + 1] = 1;

        memo = new int[n + 2][n + 2];
        for (int[] row : memo) Arrays.fill(row, UNPROCESSED);

        return search(0, n + 1);
    }

    private int search(int l, int r) {
        if (r - l < 2) return 0; // range (l, r)
        if (memo[l][r] != UNPROCESSED) return memo[l][r];

        // for each p = [l + 1, r - 1] = [l + 1, r)
        int result = 0;
        final int productOfLR = nums[l] * nums[r];
        for (int p = l + 1; p < r; p++) {
            result = Math.max(result, productOfLR * nums[p] + search(l, p) + search(p, r));
        }
        return memo[l][r] = result;
    }
}
