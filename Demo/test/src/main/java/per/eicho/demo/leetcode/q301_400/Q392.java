package per.eicho.demo.leetcode.q301_400;

/**
 * <p>392. Is Subsequence 的题解代码 </p>
 * 
 * <pre>
 *  Given two strings s and t, check if s is a subsequence of t.
 * 
 *  A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) 
 *  of the characters without disturbing the relative positions of the remaining characters. 
 *  (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *  
 * Example 1: 
 *    Input: s = "abc", t = "ahbgdc"
 *    Output: true
 * 
 * Example 2:
 *    Input: s = "axc", t = "ahbgdc"
 *    Output: false
 * 
 *  Constraints:
 *   1. 0 <= s.length <= 100
 *   2. 0 <= t.length <= 104
 *   3. s and t consist only of lowercase English letters.
 * </pre>
 * @see <a href="https://leetcode.com/problems/is-subsequence/">392. Is Subsequence</a>
 */
final public class Q392 {
    public boolean isSubsequence(String s, String t) {
        int cursor = 0;
        for (char ch: s.toCharArray()) {
            final int matchResult = match(ch, t, cursor); 
            if (-1 == matchResult) {
                return false;
            } 
            cursor = matchResult + 1;
        }
        return true;
    }

    private int match(final char ch, final String t, int cursor) {
        for (; cursor < t.length(); cursor++) {
            final char tCh = t.charAt(cursor);

            if (ch == tCh) {
                return cursor;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Q392 q392 = new Q392();
        System.out.println(q392.isSubsequence("aaaaaa", "bbaaaa"));
    }
}
