package per.eicho.demo.leetcode.q2101_2200;

/**
 * <p>2124. Check if All A's Appears Before All B's 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/check-if-all-as-appears-before-all-bs/">
 *   2124. Check if All A's Appears Before All B's</a>
 */
public final class Q2124 {
    public boolean checkString(String s) {
        // 1. 1 <= s.length <= 100
        // 2. s[i] is either 'a' or 'b'.
        final int len = s.length();
        int lastA = -1;
        int firstB = len;

        for (int i = 0; i < s.length(); i++) {
            final char ch = s.charAt(i);

            if (ch == 'a') {
                lastA = i;
                continue;
            }

            if (i < firstB) firstB = i;
        }

        return lastA > firstB ? false : true;
    }
}
