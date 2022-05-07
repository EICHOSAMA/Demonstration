package per.eicho.demo.leetcode.q1501_1600;

/**
 * <p>1544. Make The String Great 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/make-the-string-great/">
 *   1544. Make The String Great</a>
 */
public final class Q1544 {
    public String makeGood(String s) {
        // 1. 1 <= s.length <= 100
        // 2. s contains only lower and upper case English letters.
        final int n = s.length();                
        final StringBuilder sb = new StringBuilder(n);
        final int offset = 'A' - 'a';
        for (int i = 0; i < n; i++) {
            final char ch = s.charAt(i);
            if (sb.length() == 0) {
                sb.append(ch);
                continue;
            }
            
            final char lastCh = sb.charAt(sb.length() - 1);
            if (ch == lastCh + offset || ch == lastCh - offset) {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}
