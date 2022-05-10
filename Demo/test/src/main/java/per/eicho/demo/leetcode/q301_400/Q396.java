package per.eicho.demo.leetcode.q301_400;

/**
 * <p>396. Rotate Function 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/">
 *   396. Rotate Function</a>
 */
public final class Q396 {
    public int maxRotateFunction(int[] nums) {
        // 1. n == nums.length
        // 2. 1 <= n <= 10^5
        // 3. -100 <= nums[i] <= 100
        final int n = nums.length;
        final int sum = getSum(nums);
        int result = 0;
        for (int i = 0; i < n; i++) result += i * nums[i];
        int currentF = result;
        for (int i = n - 1; i >= 0; i--) {
            final int num = nums[i];
            currentF = currentF + sum - n * num;
            result = Math.max(result, currentF);
        }
        return result;
    }

    private int getSum(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        return sum;
    }
}
