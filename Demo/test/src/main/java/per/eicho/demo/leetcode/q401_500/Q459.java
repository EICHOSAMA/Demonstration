package per.eicho.demo.leetcode.q401_500;

/**
 * <p>459. Repeated Substring Pattern 的题解代码 </p>
 * 
 * <pre>
 *  Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
 * 
 * Example 1:
 *    Input: s = "abab"
 *    Output: true
 *    Explanation: It is the substring "ab" twice.
 * 
 * Example 2:
 *    Input: s = "aba"
 *    Output: false
 * 
 * Example 3:
 *    Input: s = "abcabcabcabc"
 *    Output: true
 *    Explanation: It is the substring "abc" four times or the substring "abcabc" twice.
 * 
 *  Constraints:
 *   1. 1 <= s.length <= 104
 *   2. s consists of lowercase English letters.
 * </pre>
 * @see <a href="https://leetcode.com/problems/repeated-substring-pattern/">459. Repeated Substring Pattern</a>
 */
final public class Q459 {
    public boolean repeatedSubstringPattern(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            final int subStrLen = i + 1;

            if (isValid(s, subStrLen) == true) {
                return true;
            }
        }
        return false;   
    }

    private boolean isValid(final String s, final int subStrLen) {
        final int len = s.length();

        if (len % subStrLen != 0) {
            return false;
        }

        final int times = len / subStrLen;
        for (int j = 0; j < times; j++) {
            final int l = j * subStrLen;
            
            for (int k = 0; k < subStrLen; k++) {
                if (s.charAt(k) != s.charAt(l + k)) {
                    return false;
                }
            }
        }
        return true;
    }
}
