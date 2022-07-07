package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1358. Number of Substrings Containing All Three Characters 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/">
 *   1358. Number of Substrings Containing All Three Characters</a>
 */
public final class Q1358 {
    public int numberOfSubstrings(String s) {
        // 1. 3 <= s.length <= 5 x 10^4
        // 2. s only consists of a, b or c characters.
        final int n = s.length();
        final int[] count = new int[3]; // count a,b,c
        int l = 0, r = 0; // [l, r)
        int cnt = 3;
        int result = 0;
        do {
            while (r < n && cnt > 0) {
                final int ch = s.charAt(r++) - 'a';
                count[ch]++;
                if (count[ch] == 1) cnt--;
            }

            if (cnt > 0) break;
            // [l, r) ok
            result += (n - r + 1);
            final int ch = s.charAt(l++) - 'a';
            count[ch]--;
            if (count[ch] == 0) cnt++;
        } while (true);
        return result;
    }
}
