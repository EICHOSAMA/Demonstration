package per.eicho.demo.leetcode.q901_1000;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>961. N-Repeated Element in Size 2N Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/n-repeated-element-in-size-2n-array/">961. N-Repeated Element in Size 2N Array</a>
 */
public final class Q961 {
    public int repeatedNTimes(int[] nums) {
        // 1. 2 <= n <= 5000
        // 2. nums.length == 2 * n
        // 3. 0 <= nums[i] <= 10^4
        // 4. nums contains n + 1 unique elements and one of them is repeated exactly n times.        
        final Set<Integer> set = new HashSet<>();
        for (Integer num : nums) {
            if (set.contains(num)) return num;
            set.add(num);
        }
        // impossible case
        return -1;
    }
}
