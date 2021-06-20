package per.eicho.demo.leetcode.q201_300;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>228. Summary Ranges 的题解代码 </p>
 * 
 * <pre>
 *  You are given a sorted unique integer array nums.
 *  Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
 *  Each range [a,b] in the list should be output as:
 *   1. "a->b" if a != b
 *   2. "a" if a == b
 * 
 *  Constraints:
 *   1. 0 <= nums.length <= 20
 *   2. -231 <= nums[i] <= 231 - 1
 *   3. All the values of nums are unique.
 *   4. nums is sorted in ascending order.
 * </pre>
 * @see <a href="https://leetcode.com/problems/summary-ranges/">228. Summary Ranges</a>
 */
final public class Q228 {
    public List<String> summaryRanges(int[] nums) {
        final List<String> results = new ArrayList<String>();
        
        if (nums.length == 0) {
            return results;
        }

        int left = nums[0];
        int right = nums[0];

        for (int i = 1; i < nums.length; i++) {
            final int num = nums[i];

            if (num - right == 1) {
                right = num;
                continue;
            } else {
                results.add(generateRangeStr(left, right));

                left = num;
                right = num;
            }
        }

        results.add(generateRangeStr(left, right));

        return results;
    }

    private String generateRangeStr(int l, int r) {
        if (l == r) {
            return String.valueOf(l);
        } else {
            return new StringBuilder().append(l).append("->").append(r).toString();
        }
    }
}
