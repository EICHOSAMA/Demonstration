package per.eicho.demo.leetcode.q1601_1700;

/**
 * <p>1663. Smallest String With A Given Numeric Value 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/smallest-string-with-a-given-numeric-value/">
 *   1663. Smallest String With A Given Numeric Value</a>
 */
public final class Q1663 {
    public String getSmallestString(int n, int k) {
        // 1. 1 <= n <= 10^5
        // 2. n <= k <= 26 * n
        // Tested: char[] > StringBuilder > StringBuffer
        //         22ms   > 53ms          > 99ms
        final char[] buffer = new char[n]; 
        int p = 0;

        int remainLen = n;
        int remainScore = k;
        while (remainLen > 0) {
            remainLen--;

            if (remainLen * 26 + 1 >= remainScore) {
                buffer[p++] = 'a';
                remainScore -= 1;
                continue;
            }

            // remainLen * 26 + 1 < remainScore;
            char ch = (char)((remainScore - remainLen * 26) - 1 + 'a');
            buffer[p++] = ch;

            while (remainLen > 0) {
                buffer[p++] = 'z';
                remainLen--;
            }
        }
        return String.valueOf(buffer);
    }
}
