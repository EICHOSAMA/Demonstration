package per.eicho.demo.leetcode.q301_400;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <p>398. Random Pick Index 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/random-pick-index/">398. Random Pick Index</a>
 */
@SuppressWarnings("unused")
public final class Q398 {
    private final static class Solution {

        private Map<Integer, List<Integer>> val2Indexes = new HashMap<>();
        private final Random random;

        public Solution(int[] nums) {
            final int n = nums.length;
            for (int i = 0; i < n; i++) {
                final int num = nums[i];
                if (!val2Indexes.containsKey(num)) val2Indexes.put(num, new ArrayList<>());
                val2Indexes.get(num).add(i);
            }

            random = new Random(System.currentTimeMillis());
        }
        
        public int pick(int target) {
            final List<Integer> indexes = val2Indexes.get(target);
            final int randomIdx = random.nextInt(indexes.size());
            return indexes.get(randomIdx);
        }
    }
}
