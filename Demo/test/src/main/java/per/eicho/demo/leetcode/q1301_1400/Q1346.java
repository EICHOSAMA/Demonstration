package per.eicho.demo.leetcode.q1301_1400;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>1346. Check If N and Its Double Exist 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/check-if-n-and-its-double-exist/">
 *   1346. Check If N and Its Double Exist</a>
 */
public final class Q1346 {
    public boolean checkIfExist(int[] arr) {
        // 1. 2 <= arr.length <= 500
        // 2. -10^3 <= arr[i] <= 10^3
        final Map<Integer, Integer> nums = new HashMap<>();
        for (Integer num : arr) {
            if (!nums.containsKey(num)) {
                nums.put(num, 1);
            } else {
                nums.put(num, nums.get(num) + 1);
            }
        }

        for (Integer num : nums.keySet()) {
            if (num != 0) {
                if (nums.containsKey(num * 2)) return true;
            } else {
                // num == 0;
                if (nums.get(num) >= 2) return true;
            }
        }
        return false;
    }
}
