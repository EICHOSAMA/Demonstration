package per.eicho.demo.leetcode.q2201_2300;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>2215. Find the Difference of Two Arrays 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-the-difference-of-two-arrays/">2215. Find the Difference of Two Arrays</a>
 */
public final class Q2215 {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        // 1. 1 <= nums1.length, nums2.length <= 1000
        // 2. -1000 <= nums1[i], nums2[i] <= 1000
        final int[] marks = new int[2001]; // [0, 2000] → [-1000, 1000]
        final int offset = 1000;
        for (int num : nums1) {
            num += offset;
            marks[num] = 1;
        }

        for (int num : nums2) {
            num += offset;
            if (marks[num] >= 1) {
                marks[num] = 2; // mark as both appeared at two aray.
            } else {
                marks[num] = -1;
            }
        }
        
        List<List<Integer>> result = new ArrayList<>(2);
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());
        
        for (int i = 0; i <= 2000; i++) {
            final int mark = marks[i];
            if (mark == 0 || mark == 2) continue;
            if (mark < 0) {
                result.get(1).add(i - offset);
            } else {
                result.get(0).add(i - offset);
            }
        }
        return result;
    }
}
