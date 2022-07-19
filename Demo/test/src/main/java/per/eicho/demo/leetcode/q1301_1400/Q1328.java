package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1328. Break a Palindrome 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/break-a-palindrome/">
 *   1328. Break a Palindrome</a>
 */
public final class Q1328 {
    public String breakPalindrome(String palindrome) {
        // 1. 1 <= palindrome.length <= 1000
        // 2. palindrome consists of only lowercase English letters.
        final int n = palindrome.length();
        if (n == 1) return "";
        final StringBuilder sb = new StringBuilder(palindrome);
        final int mid = ((n - 1) / 2) + (n % 2 == 1 ? -1 : 0);
        // [0, mid];
        for (int i = 0; i <= mid; i++) {
            if (sb.charAt(i) == 'a') continue;
            sb.setCharAt(i, 'a');
            return sb.toString();
        }

        sb.setCharAt(n - 1, 'b');
        return sb.toString();
    }
}
