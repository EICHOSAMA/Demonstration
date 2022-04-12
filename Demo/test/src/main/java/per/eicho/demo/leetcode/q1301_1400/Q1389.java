package per.eicho.demo.leetcode.q1301_1400;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>1389. Create Target Array in the Given Order 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/create-target-array-in-the-given-order/">
 *   1389. Create Target Array in the Given Order</a>
 */
public final class Q1389 {
    public int[] createTargetArray(int[] nums, int[] index) {
        // 1. 1 <= nums.length, index.length <= 100
        // 2. nums.length == index.length
        // 3. 0 <= nums[i] <= 100
        // 4. 0 <= index[i] <= i
        final int n = nums.length;
        final int[] result = new int[n];
        final List<Integer> workList = new LinkedList<>();
        for (int i = 0; i < n; i++) workList.add(index[i], nums[i]);

        int p = 0;
        for (int num : workList) result[p++] = num;
        return result;
    }
}
