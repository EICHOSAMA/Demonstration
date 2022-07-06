package per.eicho.demo.leetcode.q801_900;

/**
 * <p>848. Shifting Letters 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/shifting-letters/">
 *  848. Shifting Letters</a>
 */
public final class Q848 {
    public String shiftingLetters(String s, int[] shifts) {
        // 1. 1 <= s.length <= 10^5
        // 2. s consists of lowercase English letters.
        // 3. shifts.length == s.length
        // 4. 0 <= shifts[i] <= 10^9
        final StringBuilder sb = new StringBuilder(s);
        final int n = s.length();
        int sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            final int ch = s.charAt(i) - 'a';
            sum = (sum + shifts[i]) % 26;
            final char newCh = (char)('a' + ((ch + sum) % 26));
            sb.setCharAt(i, newCh);
        }
        return sb.toString();
    }
}
