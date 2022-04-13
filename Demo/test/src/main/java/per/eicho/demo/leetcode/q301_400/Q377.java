package per.eicho.demo.leetcode.q301_400;

import java.util.Arrays;

/**
 * <p>377. Combination Sum IV 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/combination-sum-iv/">
 *   377. Combination Sum IV</a>
 */
public final class Q377 {
    public int combinationSum4(int[] nums, int target) {
        // 1. 1 <= nums.length <= 200
        // 2. 1 <= nums[i] <= 1000
        // 3. All the elements of nums are unique.
        // 4. 1 <= target <= 1000
        final int n = nums.length;        
        Arrays.sort(nums);
        final int[] f = new int[target + 1];
        f[0] = 1;

        for (int i = 0; i < target; i++) {
            final int fi = f[i];
            if (fi == 0) continue;
            for (int j = 0; j < n; j++) {
                final int idx = i + nums[j];
                if (idx > target) break;

                f[idx] += fi;
            }
        }
        return f[target];
    }
}
