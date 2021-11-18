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
        final List<Integer> result = new ArrayList<>();

        int i = 0;
        while (i < nums.length) {
            // 1 <= nums[i] <= n
            int num = nums[i];

            if (num == i + 1) {
                // num placed at right location
                i++;
                continue;
            }

            // num placed at wrong location
            int temp = nums[num - 1]; // cache.
            if (temp != num) {
                nums[num - 1] = num; // place num at right location.
                nums[i] = temp; //
            } else {
                i++;
                continue;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
