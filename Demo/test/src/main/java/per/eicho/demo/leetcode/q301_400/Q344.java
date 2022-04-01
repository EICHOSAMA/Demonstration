package per.eicho.demo.leetcode.q301_400;

/**
 * <p>344. Reverse String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/remove-invalid-parentheses/">
 *   344. Reverse String</a>
 */
public final class Q344 {
    public void reverseString(char[] s) {
        // 1. 1 <= s.length <= 10^5
        // 2. s[i] is a printable ascii character.        
        final int n = s.length;
        for (int l = 0, r = n - 1; l < r; l++, r--) {
            final char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
        }
    }
}
