package per.eicho.demo.leetcode.q501_600;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>560. Subarray Sum Equals K 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/subarray-sum-equals-k/">560. Subarray Sum Equals K</a>
 */
public final class Q560 {
    public int subarraySum(int[] nums, int k) {
        // 1. 1 <= nums.length <= 2 * 10^4
        // 2. -1000 <= nums[i] <= 1000
        // 3. -10^7 <= k <= 10^7
        final int n = nums.length;
        final Map<Integer, Integer> map = new HashMap<>();
        int count = 0, sum = 0;
        map.put(0, 1);
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            final Integer target = sum - k;
            if (map.containsKey(target)) count += map.get(target);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        Q560 q560 = new Q560();

        System.out.println(q560.subarraySum(new int[]{1,2,3}, 3));
    }
}
