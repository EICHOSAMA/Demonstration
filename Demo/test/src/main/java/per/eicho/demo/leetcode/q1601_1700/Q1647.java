package per.eicho.demo.leetcode.q1601_1700;

import java.util.Arrays;

/**
 * <p>1647. Minimum Deletions to Make Character Frequencies Unique 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/">
 *   1647. Minimum Deletions to Make Character Frequencies Unique</a>
 */
public final class Q1647 {
    public int minDeletions(String s) {
        // 1 <= s.length <= 10^5
        // s contains only lowercase English letters.
        final int n = s.length();
        final int[] counts = new int[26];
        for (int i = 0; i < n; i++) counts[s.charAt(i) - 'a']++;
        Arrays.sort(counts);
        int result = 0;
        int max = Integer.MAX_VALUE;
        for (int i = 25; i >= 0; i--) {
            final int count = counts[i];
            if (count == 0) break;
            if (count < max) {
                max = count;
                continue;
            }

            if (max > 0) max--;
            result += (count - max);
        }
        return result;
    }
}
