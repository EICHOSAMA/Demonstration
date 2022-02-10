package per.eicho.demo.leetcode.q501_600;

import java.util.HashMap;

/**
 * <p>560. Subarray Sum Equals K 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/subarray-sum-equals-k/">560. Subarray Sum Equals K</a>
 */
public class Q560OfficalHashMapSolution {
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
