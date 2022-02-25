package per.eicho.demo.leetcode.q201_300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>229. Majority Element II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/majority-element-ii/">229. Majority Element II</a>
 */
public final class Q229 {
    public List<Integer> majorityElement(int[] nums) {
        // 1. 1 <= nums.length <= 5 * 10^4
        // 2. -10^9 <= nums[i] <= 10^9        
        final int n = nums.length;
        final int nOneThird = n / 3;
        final List<Integer> result = new ArrayList<>();
        Arrays.sort(nums);

        int p = 0;
        while (p < n) {
            final int num = nums[p];
            final int right = p + nOneThird;

            if (right < n && nums[right] == num) {
                result.add(num);
                p = right;
            }

            while (p + 1 < n && nums[p + 1] == num) p++;
            p++;
        }

        return result;
    }
}
