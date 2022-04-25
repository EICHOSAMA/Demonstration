package per.eicho.demo.leetcode.q2201_2300;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>2248. Intersection of Multiple Arrays 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/intersection-of-multiple-arrays/">
 *   2248. Intersection of Multiple Arrays</a>
 */
public final class Q2248 {
    public List<Integer> intersection(int[][] nums) {
        // 1. 1 <= nums.length <= 1000
        // 2. 1 <= sum(nums[i].length) <= 1000
        // 3. 1 <= nums[i][j] <= 1000
        // 4. All the values of nums[i] are unique. 
        final int[] bucket = new int[1001]; // [0, 1000]
        final int n = nums.length;
        final List<Integer> result = new ArrayList<>();

        for (int[] row : nums) {
            for (int num : row) {
                bucket[num]++;
            }
        }

        for (int i = 1; i <= 1000; i++) {
            if (bucket[i] == n) result.add(i);
        }
        
        return result;
    }
}
