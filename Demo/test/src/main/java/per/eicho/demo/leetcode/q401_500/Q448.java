package per.eicho.demo.leetcode.q401_500;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>448. Find All Numbers Disappeared in an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/">448. Find All Numbers Disappeared in an Array</a>
 */
public final class Q448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        // 1. n == nums.length
        // 2. 1 <= n <= 10^5
        // 3. 1 <= nums[i] <= n
        final int n = nums.length;
        for (int i = 0; i < n; i++) {
            final int num = nums[i];

            if (num == i + 1) continue;
            if (nums[num - 1] != num) swap(nums, i--, num - 1);
        }

        final List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            final int num = nums[i];
            if (num == i + 1) continue;
            result.add(i + 1);
        }
        return result;
    }

    private void swap(int[] nums, int i, int j) {
        final int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
