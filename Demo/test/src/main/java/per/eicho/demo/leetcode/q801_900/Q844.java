package per.eicho.demo.leetcode.q801_900;

/**
 * <p>836. Rectangle Overlap 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/rectangle-overlap/">836. Rectangle Overlap</a>
 */
public final class Q844 {
    public boolean backspaceCompare(String s, String t) {
        // 1. 1 <= s.length, t.length <= 200
        // 2. s and t only contain lowercase letters and '#' characters.        
        return process(s).equals(process(t));
    }

    private String process(String str) {
        final StringBuilder sb = new StringBuilder(str.length());
        
        int p = 0;
        while (p < str.length()) {
            final char ch = str.charAt(p++);
            if (ch != '#') {
                sb.append(ch);
                continue;
            }

            if (sb.length() == 0) continue;
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}
