package per.eicho.demo.leetcode.q101_200;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>128. Longest Consecutive Sequence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-consecutive-sequence/">128. Longest Consecutive Sequence</a>
 */
public final class Q128 {

    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums) {
            numSet.add(num);
        }

        int result = 0;

        for (int num : numSet) {
            if (numSet.contains(num - 1)) continue;

            int currentNum = num;
            int currentLen = 1;
            while (numSet.contains(currentNum + 1)) {
                currentNum += 1;
                currentLen += 1;
            }

            result = Math.max(result, currentLen);
        }

        return result;
    }
}
