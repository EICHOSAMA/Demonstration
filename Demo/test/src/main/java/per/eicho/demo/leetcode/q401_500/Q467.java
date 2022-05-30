package per.eicho.demo.leetcode.q401_500;

/**
 * <p>467. Unique Substrings in Wraparound String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/unique-substrings-in-wraparound-string/">
 *   467. Unique Substrings in Wraparound String</a>
 */
public final class Q467 {
    public int findSubstringInWraproundString(String p) {
        // 1. 1 <= p.length <= 10^5
        // 2. p consists of lowercase English letters.
        final int n = p.length();
        final int[] f = new int[26]; // 'a' - 'z'

        int i = 0;
        while (i < n) {
            final int l = i;
            while (i + 1 < n && isNext(p.charAt(i), p.charAt(i + 1))) i++;
            final int r = i;

            int len = r - l + 1;
            for (int j = l; j <= r && j < l + 26; j++, len--) {
                final char ch = p.charAt(j);
                final int idx = ch - 'a';
                f[idx] = Math.max(f[idx], len);
            }

            i++;
        }

        int sum = 0;
        for (int num : f) sum += num;
        return sum;
    }

    private boolean isNext(char a, char b) {
        if (a == 'z') return b == 'a';
        return (int)(b - a) == 1;
    }
}
