package per.eicho.demo.leetcode.q1101_1200;

import java.util.Arrays;

/**
 * <p>1155. Number of Dice Rolls With Target Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/">
 *  1155. Number of Dice Rolls With Target Sum</a>
 */
public final class Q1155 {

    int[][] memo;
    int k;
    private static final int MODULO = 1_000_000_000 + 7;

    public int numRollsToTarget(int n, int k, int target) {
        // 1. 1 <= n, k <= 30
        // 2. 1 <= target <= 1000
        memo = new int[n + 1][target + 1];
        for (int[] row : memo) Arrays.fill(row, -1);
        Arrays.fill(memo[0], 0);
        memo[0][0] = 1;
        this.k = k;
        return search(n, target);
    }

    private int search(int n, int target) {
        if (target < 0) return 0;
        if (memo[n][target] != -1) return memo[n][target];
        int result = 0;
        for (int i = 1; i <= k; i++) result = (result + search(n - 1, target - i)) % MODULO;
        return memo[n][target] = result;
    }
}
