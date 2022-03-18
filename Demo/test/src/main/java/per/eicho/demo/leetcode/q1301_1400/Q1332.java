package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1332. Remove Palindromic Subsequences 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/remove-palindromic-subsequences/">
 *   1332. Remove Palindromic Subsequences</a>
 */
public final class Q1332 {
    public int removePalindromeSub(String s) {
        // 1. 1 <= s.length <= 1000
        // 2. s[i] is either 'a' or 'b'.        
        final int n = s.length();
        for (int i = 0; i < n / 2; ++i) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) return 2;
        }
        return 1;
    }
}
