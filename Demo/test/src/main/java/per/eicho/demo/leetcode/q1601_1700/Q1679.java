package per.eicho.demo.leetcode.q1601_1700;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>1679. Max Number of K-Sum Pairs 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/max-number-of-k-sum-pairs/">
 *   1679. Max Number of K-Sum Pairs</a>
 */
public final class Q1679 {
    public int maxOperations(int[] nums, int k) {
        // 1. 1 <= nums.length <= 10^5
        // 2. 1 <= nums[i] <= 10^9
        // 3. 1 <= k <= 10^9
        final Map<Integer, int[]> numCounts = new HashMap<>();

        int result = 0;
        for (int num : nums) {
            final int target = k - num;
            if (target < 1) continue;
            
            if (numCounts.containsKey(target)) {
                final int count = --numCounts.get(target)[0];
                result++;
                if (count == 0) numCounts.remove(target);
                continue;
            }

            if (!numCounts.containsKey(num)) {
                numCounts.put(num, new int[]{1});
            } else {
                numCounts.get(num)[0]++;
            }
        }

        return result;
    }
}
