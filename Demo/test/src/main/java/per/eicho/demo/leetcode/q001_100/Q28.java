package per.eicho.demo.leetcode.q001_100;

import per.eicho.demo.algorithm.string.kmp.KMPSample;

/**
 * <p>28. Implement strStr() 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/implement-strstr/">28. Implement strStr()</a>
 * @see {@link KMPSample KMP Sample}
 */
public final class Q28 {
    public int strStr(String haystack, String needle) {
        // 1. 0 <= haystack.length, needle.length <= 5 * 10^4
        // 2. haystack and needle consist of only lower-case English characters.
        final int n = haystack.length();
        final int m = needle.length();

        if (m == 0) return 0;
        final int[] pmt = createPMT(needle);

        int i = 0, j = 0;
        while (true) {
            if (j == m) return i - j;
            if (i == n) return -1;

            if (haystack.charAt(i) == needle.charAt(j)) {
                i++; j++;
            } else if (j == 0) {
                i++;
            } else {
                j = pmt[j - 1];
            }
        }
    }

    private int[] createPMT(String needle) {
        final int m = needle.length();
        final int[] pmt = new int[m];

        int prefix = 0;
        int suffix = 1;
        while (suffix < m) {
            if (needle.charAt(prefix) == needle.charAt(suffix)) {
                pmt[suffix++] = ++prefix;
            } else if (prefix == 0) {
                pmt[suffix++] = 0;
            } else {
                prefix = pmt[prefix - 1];
            }
        }
        return pmt;
    }
}
