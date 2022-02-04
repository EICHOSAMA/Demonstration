package per.eicho.demo.leetcode.q501_600;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>523. Continuous Subarray Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/continuous-subarray-sum/">523. Continuous Subarray Sum</a>
 */
public final class Q523 {
    public boolean checkSubarraySum(int[] nums, int k) {
        // 1. 1 <= nums.length <= 10^5
        // 2. 0 <= nums[i] <= 10^9
        // 3. 0 <= sum(nums[i]) <= 23^1 - 1
        // 4. 1 <= k <= 23^1 - 1
        final int n = nums.length;
        final Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = (sum + nums[i]) % k;

            final Integer iSum = sum;
            if (map.containsKey(iSum)) {
                if (i - map.get(iSum) >= 2) return true;
            } else {
                map.put(iSum, i); // record first
            } 
        }

        return false;
    }
}
