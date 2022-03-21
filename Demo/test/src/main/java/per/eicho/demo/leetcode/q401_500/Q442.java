package per.eicho.demo.leetcode.q401_500;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>442. Find All Duplicates in an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-all-duplicates-in-an-array/">
 *   442. Find All Duplicates in an Array</a>
 */
public final class Q442 {
    public List<Integer> findDuplicates(int[] nums) {
        // 1. n == nums.length
        // 2. 1 <= n <= 10^5
        // 3. 1 <= nums[i] <= n
        // 4. Each element in nums appears once or twice.        
        final List<Integer> result = new ArrayList<>();
        final int n = nums.length;

        int rIdx = 0;
        while (rIdx < n) {
            int num = nums[rIdx];
            int vIdx = map2VituralIndex(rIdx);
            
            if (num == vIdx) {
                rIdx++;
                continue;
            }

            while (num != vIdx && num != -1) {
                final int rIdx2 = map2RealIndex(num); // num as vitural index

                if (nums[rIdx2] == map2VituralIndex(rIdx2)) {
                    result.add(num);
                    nums[rIdx] = -1;
                    num = -1;
                } else {
                    // switch
                    nums[rIdx] = nums[rIdx2];
                    nums[rIdx2] = num;
                }

                num = nums[rIdx];
            }

            rIdx++;
        }

        return result;
    }

    private int map2VituralIndex(int rIdx) { return rIdx + 1; }
    private int map2RealIndex(int vIdx) { return vIdx - 1; }
}
