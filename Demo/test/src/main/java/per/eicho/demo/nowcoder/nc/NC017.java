package per.eicho.demo.nowcoder.nc;

/**
 * <p>最长回文 - 中心外扩 - O(n^2)</p>
 * <pre>
 *  最优解马拉车算法参考 
 *    - {@link per.eicho.demo.algorithm.string.palindromic.manacher.ManacherSample ManacherSample}类
 * </pre>
 */
public final class NC017 {
    public int getLongestPalindrome(String A) {
        final String str = A;
        final int n = str.length();
        // n: [1, 1000]
        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            int candidate = getLongestPalindrome(str, i);
            if (candidate > maxLen) maxLen = candidate;
        }

        for (int i = 0; i < n - 1; i++) {
            if (str.charAt(i) != str.charAt(i + 1)) continue;
            int candidate = getLongestPalindrome(str, i, i + 1);
            if (candidate > maxLen) maxLen = candidate;
        }

        return maxLen;
    }

    private int getLongestPalindrome(String str, int center) {
        int l = center - 1, r = center + 1, len = str.length(), ll = 1;
        while (l >= 0 && r < len && str.charAt(l) == str.charAt(r)) {
            l--; r++; ll += 2;
        }
        return ll;
    }

    private int getLongestPalindrome(String str, int centerL, int centerR) {
        int l = centerL - 1, r = centerR + 1, len = str.length(), ll = 2;
        while (l >= 0 && r < len && str.charAt(l) == str.charAt(r)) {
            l--; r++; ll += 2;
        }        
        return ll;
    }
}
