package per.eicho.demo.leetcode.q1601_1700;

/**
 * <p>1658. Minimum Operations to Reduce X to Zero 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/">
 *   1658. Minimum Operations to Reduce X to Zero</a>
 */
public final class Q1658 {
    public int minOperations(int[] nums, int x) {
        // 1. 1 <= nums.length <= 10^5
        // 2. 1 <= nums[i] <= 10^4
        // 3. 1 <= x <= 10^9
        int result = Integer.MAX_VALUE;
        final int n = nums.length;
        int l = 0, r = n - 1;
        int sum = 0;
        while (r >= l && sum < x) sum += nums[r--];

        while (l < n) {
            if (r < l && sum < x) return -1;

            // [0, l) & (r, n - 1]
            if (sum == x) result = Math.min(result, l + n - 1 - r);
            sum += nums[l++];
            while (r + 1 < n && sum > x) sum -= nums[++r];
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static void main(String[] args) {
        Q1658 q1658 = new Q1658();
        // System.out.println(q1658.minOperations(new int[]{1,1,4,2,3}, 5));
        System.out.println(q1658.minOperations(new int[]{8828,9581,49,9818,9974,9869,9991,10000,
            10000,10000,9999,9993,9904,8819,1231,6309}, 134365));
    }
}
