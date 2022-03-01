package per.eicho.demo.leetcode.q201_300;

/**
 * <p>213. House Robber II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/house-robber-ii/">213. House Robber II</a>
 */
public final class Q213 {
    public int rob(int[] nums) {
        // 1. 1 <= nums.length <= 100
        // 2. 0 <= nums[i] <= 1000
        final int n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
    }

    public int rob(int[] nums, int l, int r) {
        // optim hint: rolling array. Space Complexity O(n) ⇒ O(1)
        // DP: F[i] = Max(F[i - 2] + nums[i], F[i - 1])
        int Fi2 = nums[l];
        int Fi1 = Math.max(nums[l], nums[l + 1]);

        for (int i = l + 2; i <= r; i++) {
            int Fi = Math.max(Fi2 + nums[i], Fi1);

            Fi2 = Fi1;
            Fi1 = Fi;
        }
        return Fi1;
    }
}
