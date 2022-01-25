package per.eicho.demo.leetcode.q2101_2200;

/**
 * <p>2138. Divide a String Into Groups of Size k 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/">
 *   2138. Divide a String Into Groups of Size k</a>
 */
public final class Q2138 {
    public String[] divideString(String s, int k, char fill) {
        // 1. 1 <= s.length <= 100
        // 2. s consists of lowercase English letters only.
        // 3. 1 <= k <= 100
        // 4. fill is a lowercase English letter.
        int len = s.length();
        if (len % k != 0) {
            len = len + (k - (len % k));
        }
        final String[] result = new String[len / k]; 
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < len) {
            sb.append(fill);
        }

        s = sb.toString();
        for (int i = 0; i < result.length; i++) {
            result[i] = s.substring(i * k, i * k + k);
        }

        return result;
    }
}
