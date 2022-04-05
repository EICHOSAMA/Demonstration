package per.eicho.demo.leetcode.q401_500;

import java.util.Arrays;

/**
 * <p>470. Implement Rand10() Using Rand7() </p>
 * 
 * @see <a href="https://leetcode.com/problems/implement-rand10-using-rand7/">
 *   470. Implement Rand10() Using Rand7()</a>
 */
public final class Q494 {
    public int findTargetSumWays(int[] nums, int target) {
        // 1. 1 <= nums.length <= 20
        // 2. 0 <= nums[i] <= 1000
        // 3. 0 <= sum(nums[i]) <= 1000
        // 4. -1000 <= target <= 1000        
        final int n = nums.length;
        final int[][] f = new int[2][40001]; // [-20000, 20000] → [0, 40000]
        final int offset = 20000;
        f[0][-nums[0] + offset]++;
        f[0][nums[0] + offset]++;
        for (int i = 1; i < n; i++) {
            final int c = i % 2;
            Arrays.fill(f[c], 0);
            final int p = (i - 1) % 2; 
            final int num = nums[i];

            for (int j = 0; j < 40001; j++) {
                final int count = f[p][j];
                if (count == 0) continue;

                f[c][j + num] += count;
                f[c][j - num] += count;
            }
        }

        return f[(n - 1) % 2][target + offset];
    }
}
