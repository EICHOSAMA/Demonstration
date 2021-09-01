package per.eicho.demo.leetcode.q1_100;

/**
 * <p>44. Wildcard Matching 的题解代码 </p>
 * 
 * <pre>
 *  Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
 *    '?' Matches any single character.
 *    '*' Matches any sequence of characters (including the empty sequence).
 *  The matching should cover the entire input string (not partial).
 * 
 * Example 1: 
 *    Input: s = "aa", p = "a"
 *    Output: false
 *    Explanation: "a" does not match the entire string "aa".
 * 
 * Example 2:
 *    Input: s = "aa", p = "*"
 *    Output: true
 *    Explanation: '*' matches any sequence.
 * 
 * Example 3:
 *    Input: s = "cb", p = "?a"
 *    Output: false
 *    Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 * 
 * Example 4:
 *    Input: s = "adceb", p = "*a*b"
 *    Output: true
 *    Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
 * 
 * Example 5:
 *    Input: s = "acdcb", p = "a*c?b"
 *    Output: false
 * 
 *  Constraints:
 *   1. 0 <= s.length, p.length <= 2000
 *   2. s contains only lowercase English letters.
 *   3. p contains only lowercase English letters, '?' or '*'.
 * </pre>
 * @see <a href="https://leetcode.com/problems/wildcard-matching/">44. Wildcard Matching</a>
 */
final public class Q44 {
    public static void main(String[] args) {
        final Q44 q44 = new Q44();
        
        final String s = "abcabczzzde";
        final String p = "*abc???de*";

        final boolean result = q44.isMatch(s, p);
        System.out.print(result);
    }

    public boolean isMatch(String s, String p) {

        // 1. GATHER INFO
        final int lenS = s.length();
        final int lenP = p.length();

        // 2. prepare space to store information
        boolean[][] f = new boolean[lenS + 1][lenP + 1];

        // 3. DP
        f[0][0] = true;

        for (int i = 0; i < lenS; i++) {
            final char chS = s.charAt(i);

            for (int j = 0; j < lenP; j++) {
                if (!f[i][j]) continue;

                final char chP = p.charAt(j);

                if (isEnglishLetter(chP)) {
                    // case1: english letter
                    if (chS == chP) {
                        f[i + 1][j + 1] = true;
                    }
                } else if (isQuestionMark(chP)) {
                    // case2: question mark
                    f[i + 1][j + 1] = true;
                } else {
                    // case3: *
                    f[i + 1][j + 1] = true; // * match 1 character
                    f[i + 1][j] = true; // * match more than one character.
                    f[i][j + 1] = true; // * match 0 character.
                }
            }
        }

        final int lastIdx = lenS;
        for (int j = 0; j < lenP; j++) {
            if (!f[lastIdx][j]) continue;

            final char chP = p.charAt(j);
            if (chP == '*') {
                f[lastIdx][j + 1] = true;
            }
        }

        return f[lenS][lenP];
    }

    private boolean isEnglishLetter(char ch) {
        return 'a' <= ch && ch <= 'z';
    }

    private boolean isQuestionMark(char ch) {
        return ch == '?';
    }
}
