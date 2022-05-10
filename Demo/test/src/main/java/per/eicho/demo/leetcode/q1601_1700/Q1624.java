package per.eicho.demo.leetcode.q1601_1700;

import java.util.Arrays;

/**
 * <p>1624. Largest Substring Between Two Equal Characters 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/largest-substring-between-two-equal-characters/">
 *   1624. Largest Substring Between Two Equal Characters</a>
 */
public final class Q1624 {
    public int maxLengthBetweenEqualCharacters(String s) {
        // 1. 1 <= s.length <= 300
        // 2. s contains only lowercase English letters.        
        int result = -1;
        final int n = s.length();
        int[] positions = new int[26];
        Arrays.fill(positions, -1);
        for (int i = n - 1; i >= 0; i--) {
            final char ch = s.charAt(i);
            final int idx = ch - 'a';
            if (positions[idx] != -1) continue;
            positions[idx] = i;
        }

        for (int i = 0; i < n; i++) {
            final char ch = s.charAt(i);
            final int idx = ch - 'a';
            result = Math.max(result, positions[idx] - i - 1);
        }

        return result;
    }
}
