package per.eicho.demo.leetcode.q301_400;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>349. Intersection of Two Arrays 的题解代码 </p>
 * 
 * <pre>
 *  Given two integer arrays nums1 and nums2, 
 *  return an array of their intersection. 
 *  Each element in the result must be unique and you may return the result in any order.
 * 
 * Example 1: 
 *    Input : nums1 = [1,2,2,1], nums2 = [2,2]
 *    Output: [2]
 * 
 * Example 2:
 *    Input : nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 *    Output: [9,4]
 *    Explanation: [4,9] is also accepted.
 * 
 *  Constraints:
 *   1. 1 <= nums1.length, nums2.length <= 1000
 *   2. 0 <= nums1[i], nums2[i] <= 1000
 * </pre>
 * @see <a href="https://leetcode.com/problems/intersection-of-two-arrays/">349. Intersection of Two Arrays</a>
 */
final public class Q349 {

    private static int MAX_NUM = 1000;

    public int[] intersection(int[] nums1, int[] nums2) {
        final int[] counts1 = getCounts(nums1);
        final int[] counts2 = getCounts(nums2);

        final List<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < MAX_NUM + 1; i++) {
            if (counts1[i] > 0 && counts2[i] > 0) {
                result.add(i);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private int[] getCounts(final int[] nums) {
        final int[] counts = new int[MAX_NUM + 1];

        for (int num: nums) {
            counts[num] = counts[num] + 1;
        }
        return counts;
    }
}
