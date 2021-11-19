package per.eicho.demo.leetcode.q601_700;

/**
 * <p>680. Valid Palindrome II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/valid-palindrome-ii/">680. Valid Palindrome II</a>
 */
public final class Q680 {
    public boolean validPalindrome(String s) {
        return validPalindrome(s, 0, s.length() - 1, true);
    }

    private boolean validPalindrome(final String s, int l, int r, boolean left) {
        if (l >= r) return true; // l == r || l > r
        final char lc = s.charAt(l);
        final char rc = s.charAt(r);
        if (lc == rc) return validPalindrome(s, l + 1, r - 1, left);
        return left && 
               (validPalindrome(s, l + 1, r, false) ||
                validPalindrome(s, l, r - 1, false));
    }
}
