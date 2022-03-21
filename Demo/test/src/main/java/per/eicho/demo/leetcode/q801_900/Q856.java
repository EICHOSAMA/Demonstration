package per.eicho.demo.leetcode.q801_900;

/**
 * <p>856. Score of Parentheses 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/score-of-parentheses/">
 *   856. Score of Parentheses</a>
 */
public final class Q856 {
    public int scoreOfParentheses(String s) {
        // 1. 2 <= s.length <= 50
        // 2. s consists of only '(' and ')'.
        // 3. s is a balanced parentheses string.        
        final int n = s.length();
        int[] stack = new int[25 + 1];
        int stackSize = 0;

        for (int i = 0; i < n; i++) {
            final char ch = s.charAt(i);
            if (ch == '(') {
                stack[stackSize++] = -1; // mark as '('
                continue;
            }

            // ')'
            if (stack[stackSize - 1] == -1) {
                stack[stackSize - 1] = 1;
                continue;
            }
            int sum = 0;
            while (stack[stackSize - 1] != -1) {
                sum += stack[--stackSize];
            }
            stack[stackSize - 1] = 2 * sum;
        }

        int result = 0;
        while (stackSize != 0) result += stack[--stackSize];
        return result;
    }
}
