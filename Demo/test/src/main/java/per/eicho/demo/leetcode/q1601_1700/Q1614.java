package per.eicho.demo.leetcode.q1601_1700;

/**
 * <p>1614. Maximum Nesting Depth of the Parentheses 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/">
 *   1614. Maximum Nesting Depth of the Parentheses</a>
 */
public final class Q1614 {
    public int maxDepth(String s) {
        // 1. 1 <= s.length <= 100
        // 2. s consists of digits 0-9 and characters '+', '-', '*', '/', '(', and ')'.
        // 3. It is guaranteed that parentheses expression s is a VPS.
        int depth = 0;

        int lv = 0;
        final int n = s.length();
        for (int i = 0; i < n; i++) {
            final char ch = s.charAt(i);
            if (ch == '(') {
                lv++;
                depth = Math.max(depth, lv);
            } else if (ch == ')') {
                lv--;
            }
        }
        return depth;
    }
}
