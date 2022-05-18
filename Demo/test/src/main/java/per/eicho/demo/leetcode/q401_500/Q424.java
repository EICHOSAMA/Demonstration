package per.eicho.demo.leetcode.q401_500;

/**
 * <p>424. Longest Repeating Character Replacement 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-repeating-character-replacement/">
 *   424. Longest Repeating Character Replacement</a>
 */
public final class Q424 {
    public int characterReplacement(String s, int k) {
        // 1. 1 <= s.length <= 10^5
        // 2. s consists of only uppercase English letters.
        // 3. 0 <= k <= s.length
        int result = 1;
        final int n = s.length();
        for (int i = 0; i < 26; i++) {
            final char ch = (char)('A' + i);

            int remain = k;
            int l = 0, r = -1;
            int maxLen = 1;
            while (++r < n) {
                if (s.charAt(r) != ch) remain--;

                while (remain < 0) {
                    if (s.charAt(l++) != ch) remain++;
                }

                maxLen = Math.max(maxLen, r - l + 1);
            }

            result = Math.max(result, maxLen);
        }
        return result;
    }
}
