package per.eicho.demo.leetcode.q201_300;

/**
 * <p>214. Shortest Palindrome 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/shortest-palindrome/">214. Shortest Palindrome</a>
 * @see <a href="https://en.wikipedia.org/wiki/Rabin–Karp_algorithm">Rabin–Karp algorithm</a>
 * @see <a href="https://en.wikipedia.org/wiki/Rabin_fingerprint">Rabin fingerprint</a>
 */
public final class Q214 {
    public String shortestPalindrome(String s) {
        // 1. 0 <= s.length <= 5 * 10^4
        // 2. s consists of lowercase English letters only.
        final int n = s.length();
        if (n == 0) return "";

        final int MOD = 1000_000_000 + 7; // 10^9 + 7 
        final int BASE = 256;

        int hashL = 0, hashR = 0, mul = 1;
        int longest = 1;
        for (int i = 0; i < n; i++) {
            hashL = (int) (((long) hashL * BASE + s.charAt(i)) % MOD);
            hashR = (int) ((hashR + (long) mul * s.charAt(i)) % MOD);
            if (hashL == hashR) longest = i;
            mul = (int) ((long) mul * BASE % MOD);
        }

        // WARNING!!!: same hash shoule be checked @see isPalindrome method.

        final String append = (longest == n - 1 ? "" : s.substring(longest + 1));
        return new StringBuilder(append).reverse().append(s).toString();
    }

    public boolean isPalindrome(String s, int r) {
        int l = 0;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}
