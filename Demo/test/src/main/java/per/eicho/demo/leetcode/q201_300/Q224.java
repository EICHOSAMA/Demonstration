package per.eicho.demo.leetcode.q201_300;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>224. Basic Calculator 的题解代码 </p>
 * 
 * <pre>
 *  运算优先级：
 *   1: +,-
 *   2: *,/
 *   3: 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/basic-calculator/">224. Basic Calculator</a>
 */
public final class Q224 {
    public int calculate(String s) {
        // 1. 1 <= s.length <= 3 * 10^5
        // 2. s consists of digits, '+', '-', '(', ')', and ' '.
        // 3. s represents a valid expression.
        // 4. '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
        // 5. '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
        // 6. There will be no two consecutive operators in the input.
        // 7. Every number and running calculation will fit in a signed 32-bit integer.

        Deque<Integer> ops = new LinkedList<Integer>();
        
        ops.push(1);
        int sign = 1;

        int result = 0;
        final int len = s.length();
        int p = 0;
        while (p < len) {
            char ch = s.charAt(p++);
            
            if (ch == ' ') continue;

            if (ch == '+') {
                sign = ops.peek();
            } else if (ch == '-') {
                sign = -ops.peek();
            } else if (ch == '(') {
                ops.push(sign);
            } else if (ch == ')') {
                ops.pop();
            } else {
                // DIGIT CASE
                // Every number and running calculation will fit in a signed 32-bit integer.
                int num = ch - '0';
                while (p < len && isDigit(ch = s.charAt(p))) {
                    num = num * 10 + (ch - '0');
                    p++;
                }
                result += sign * num;
            }
        }
        return result;
    }

    private boolean isDigit(char ch) { return '0' <= ch && ch <= '9'; }

    public static void main(String[] args) {
        Q224 q224 = new Q224();
        System.out.println(q224.calculate("1 + 1"));
        System.out.println(q224.calculate(" -2-1 + 2 "));
        System.out.println(q224.calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(q224.calculate("-1--2--3"));
        
    }
}
