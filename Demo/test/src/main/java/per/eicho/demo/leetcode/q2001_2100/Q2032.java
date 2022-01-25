package per.eicho.demo.leetcode.q2001_2100;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>2032. Two Out of Three 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/two-out-of-three/">
 *   2032. Two Out of Three</a>
 */
public final class Q2032 {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        // 1. 1 <= nums1.length, nums2.length, nums3.length <= 100
        // 2. 1 <= nums1[i], nums2[j], nums3[k] <= 100
        final Map<Integer, Integer> map = new HashMap<>(); // Map<Number, Count>

        for (int i = 0; i < nums1.length; i++) {
            final int num = nums1[i];

            if (!map.containsKey(num)) {
                map.put(num, 0b001);
            }
        }

        for (int i = 0; i < nums2.length; i++) {
            final int num = nums2[i];

            if (!map.containsKey(num)) {
                map.put(num, 0b010);
            } else {
                map.put(num, map.get(num) | 0b010);
            }
        }

        for (int i = 0; i < nums3.length; i++) {
            final int num = nums3[i];

            if (map.containsKey(num)) {
                map.put(num, map.get(num) | 0b100);
            }
        }

        final List<Integer> result = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            final int num = entry.getKey();
            final int mark = entry.getValue();

            if ((mark == 0b011) || (mark == 0b110) || (mark == 0b101) || (mark == 0b111)) {
                result.add(num);
            }
        }

        return result;
    }
}
