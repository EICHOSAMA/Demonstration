package per.eicho.demo.leetcode.q301_400;

/**
 * <p>330. Patching Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/patching-array/">
 *   330. Patching Array</a>
 */
public final class Q330 {
    public int minPatches(int[] nums, int n) {
        // 1. 1 <= nums.length <= 1000
        // 2. 1 <= nums[i] <= 10^4
        // 3. nums is sorted in ascending order.
        // 4. 1 <= n <= 2^31 - 1
        int result = 0;
        long bound = 0;
        for (int i = 0; i < nums.length && bound < n; i++) {
            final int num = nums[i];
            while (bound < n && bound + 1L < num) {
                result++;
                bound += (bound + 1L);
            }

            // assert num >= bound + 1;
            bound += num;
        }

        while (bound < n) {
            result++;
            bound += (bound + 1L);
        }

        return result;
    }

    public static void main(String[] args) {
        Q330 q330 = new Q330();
        System.out.println(q330.minPatches(new int[]{1000}, 20_0000_0000));
    }
}
