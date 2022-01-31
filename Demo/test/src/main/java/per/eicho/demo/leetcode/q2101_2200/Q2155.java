package per.eicho.demo.leetcode.q2101_2200;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>2155. All Divisions With the Highest Score of a Binary Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/all-divisions-with-the-highest-score-of-a-binary-array/">
 *   2155. All Divisions With the Highest Score of a Binary Array</a>
 */
public final class Q2155 {
    public List<Integer> maxScoreIndices(int[] nums) {
        // 1. n == nums.length
        // 2. 1 <= n <= 10^5
        // 3. nums[i] is either 0 or 1.
        final int n = nums.length;
        int maxScore = 0;
        final List<Integer> result = new LinkedList<>(); 

        int count0 = 0, count1 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                count0++;
            } else {
                count1++;
            }
        }

        int c0 = 0, c1 = 0;
        for (int i = 0; i < n; i++) {
            final int score = c0 + (count1 - c1);
            
            final int num = nums[i];
            if (num == 0) {
                c0++;
            } else {
                c1++;
            }

            if (score < maxScore) continue;
            if (score == maxScore) {
                result.add(i);
                continue;
            }

            result.clear();
            result.add(i);
            maxScore = score;
        }

        if (count0 == maxScore) {
            result.add(n);
        } else if (count0 > maxScore) {
            result.clear();
            result.add(n);
        }

        return result;
    }
}
