package per.eicho.demo.leetcode.q501_600;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>532. K-diff Pairs in an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/k-diff-pairs-in-an-array/">532. K-diff Pairs in an Array</a>
 */
public final class Q532 {
    public int findPairs(int[] nums, int k) {
        // 1. 1 <= nums.length <= 10^4
        // 2. -10^7 <= nums[i] <= 10^7
        // 3. 0 <= k <= 10^7
        int result = 0;
        if (k != 0) {
            final Set<Integer> appearedNums = new HashSet<>();
            for (Integer num : nums) {
                if (!appearedNums.contains(num)) {
                    appearedNums.add(num);
                    
                    if (appearedNums.contains(num + k)) result++;
                    if (appearedNums.contains(num - k)) result++;
                }
            }
    
            return result;
        } else {
            final Map<Integer, Boolean> appearedNums = new HashMap<>();
            for (Integer num : nums) {
                if (!appearedNums.containsKey(num)) {
                    appearedNums.put(num, Boolean.FALSE);
                    continue;
                }

                if (appearedNums.get(num).equals(Boolean.FALSE)) {
                    result++;
                    appearedNums.put(num, Boolean.TRUE);
                }
            }
    
            return result; 
        }
    }
}
