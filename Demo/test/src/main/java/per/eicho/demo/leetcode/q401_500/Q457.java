package per.eicho.demo.leetcode.q401_500;

/**
 * <p>457. Circular Array Loop 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/circular-array-loop/">
 *   457. Circular Array Loop</a>
 */
public final class Q457 {

    int n;

    public boolean circularArrayLoop(int[] nums) {
        // 1. 1 <= nums.length <= 5000
        // 2. 2. -1000 <= nums[i] <= 1000
        // 3. 3. nums[i] != 0
        // Follow up: Could you solve it in O(n) time complexity and O(1) extra space complexity?
        n = nums.length;

        for (int i = 0; i < n; i++) {
            if (search(nums, i, nums[i] > 0)) return true;
        }
        return false;
    }

    private boolean search(int[] nums, int p, boolean positive) {
        if (nums[p] == 0) return true;
        if (nums[p] == Integer.MAX_VALUE) return false;
        // Every nums[seq[j]] is either all positive or all negative.

        if ((nums[p] > 0 && !positive) ||
            (nums[p] < 0 && positive)) return false;

        // nums[i] != 0
        final int nextP = (p + nums[p] + n * n) % n;
        if (p == nextP) {
            nums[p] = Integer.MAX_VALUE;
            return false;
        }

        nums[p] = 0;
        final boolean result = search(nums, nextP, positive);
        if (!result) nums[p] = Integer.MAX_VALUE;
        return result;
    }

    public static void main(String[] args) {
        Q457 q457 = new Q457();
        System.out.println(q457.circularArrayLoop(new int[]{2,-1,1,2,2}));
    }
}
