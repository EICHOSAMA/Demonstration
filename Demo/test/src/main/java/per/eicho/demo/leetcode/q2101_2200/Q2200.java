package per.eicho.demo.leetcode.q2101_2200;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>2200. Find All K-Distant Indices in an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-all-k-distant-indices-in-an-array/">
 *   2200. Find All K-Distant Indices in an Array</a>
 */
public final class Q2200 {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        // 1. 1 <= nums.length <= 1000
        // 2. 1 <= nums[i] <= 1000
        // 3. key is an integer from the array nums.
        // 4. 1 <= k <= nums.length
        final int n = nums.length;
        final List<Integer> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            if (nums[i] == key) {
                result.add(i);
                continue;
            }
            for (int dis = 1; dis <= k; dis++) {
                final int l = i - dis;
                final int r = i + dis;

                if (l >= 0) {
                    if (nums[l] == key) {
                        result.add(i);
                        break;
                    }
                }

                if (r < n) {
                    if (nums[r] == key) {
                        result.add(i);
                        break;
                    }
                }
            }
        }

        return result;
    }
}
