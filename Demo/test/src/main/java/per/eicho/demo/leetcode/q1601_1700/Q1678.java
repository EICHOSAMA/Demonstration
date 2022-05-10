package per.eicho.demo.leetcode.q1601_1700;

/**
 * <p>1678. Goal Parser Interpretation 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/goal-parser-interpretation/">
 *   1678. Goal Parser Interpretation</a>
 */
public final class Q1678 {
    public String interpret(String command) {
        // 1. 1 <= command.length <= 100
        // 2. command consists of "G", "()", and/or "(al)" in some order.        
        final StringBuilder sb = new StringBuilder();
        int p = 0;
        final int n = command.length();
        while (p < n) {
            final char ch = command.charAt(p++);

            if (ch == 'G') {
                sb.append('G');
            } else {
                if (command.charAt(p) == ')') {
                    sb.append('o');
                    p++;
                } else {
                    sb.append("al");
                    p += 3;
                }
            }
        } 
        
        return sb.toString();
    }
}
