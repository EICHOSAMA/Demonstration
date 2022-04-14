package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1470. Shuffle the Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/shuffle-the-array/">
 *   1470. Shuffle the Array</a>
 */
public final class Q1470 {
    public int[] shuffle(int[] nums, int n) {
        // 1. 1 <= n <= 500
        // 2. nums.length == 2n
        // 3. 1 <= nums[i] <= 10^3
        doInplaceShuffle(nums, n, 0);
        return nums;
    }

    private void doInplaceShuffle(int[] nums, int n, int i) {
        if (i == n) return;
        final int xi = nums[i];
        final int yi = nums[i + n];

        doInplaceShuffle(nums, n, i + 1);

        nums[i * 2] = xi;
        nums[i * 2 + 1] = yi;
    }
}
