package per.eicho.demo.leetcode.q201_300;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <p>227. Basic Calculator II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/basic-calculator-ii/">227. Basic Calculator II</a>
 * @see <a href="https://ja.wikipedia.org/wiki/逆ポーランド記法">逆ポーランド記法</a>
 */
public final class Q227 {
    private static enum Operator {
        SUM(1),
        DIFF(1),
        PRODUCT(2),
        QUOTIENT(2);

        Operator(int priority) {
            this.priority = priority;
        }

        final int priority;

        static Operator parse(char ch) {
            switch (ch) {
                case '+': return SUM;
                case '-': return DIFF;
                case '*': return PRODUCT;
                case '/': return QUOTIENT;
                default: return null;
            }
        }

        int calculate(int arg1, int arg2) {
            switch (this) {
                case SUM: return arg1 + arg2;
                case DIFF: return arg1 - arg2;
                case PRODUCT : return arg1 * arg2;
                default: return arg1 / arg2;
            }
        }
    }

    private static class CalculateFrame {

        Integer arg1;
        Integer arg2;
        final Operator op;

        CalculateFrame(Operator op) {
            this.op = op;
        }

        void setArg(Integer arg) {
            if (arg2 == null) {
                arg2 = arg;
            } else {
                arg1 = arg;
            }
        }

        boolean canCalculate() {
            return arg1 != null && arg2 != null;
        }

        Integer calculate() {
            return op.calculate(arg1, arg2);
        }
    }

    private final static char SPACE = ' '; 

    public int calculate(String s) {
        // 1. 1 <= s.length <= 3 * 10^5
        // 2. s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
        // 3. s represents a valid expression.
        // 4. All the integers in the expression are non-negative integers in the range [0, 2^31 - 1].
        // 5. The answer is guaranteed to fit in a 32-bit integer.

        // 1. parse s to notations.
        final int len = s.length();
        final List<Object> notations = new ArrayList<>();
        final Stack<Operator> operatorStack = new Stack<>();
        for (int i = 0; i < len; i++) {
            final char ch = s.charAt(i);

            // case1: AS Space
            if (ch == SPACE) continue;
            
            // case2: AS operator
            Operator operator = Operator.parse(ch);
            if (operator != null) {
                while (!operatorStack.isEmpty() && operatorStack.peek().priority >= operator.priority) {
                    notations.add(operatorStack.pop());
                }
                operatorStack.add(operator);
                continue;
            }

            // case3: AS integer
            int num = ch - '0';

            while (i + 1 < len && isDigit(s.charAt(i + 1))) {
                i++;
                num = num * 10 + (s.charAt(i) - '0');
            }
            notations.add(num);
        }
        // 
        while (!operatorStack.isEmpty()) notations.add(operatorStack.pop());

        final Stack<CalculateFrame> stack = new Stack<>();
        Integer result = 0;
        for (int i = notations.size() - 1; i >= 0; i--) {
            final Object obj = notations.get(i);

            if (obj instanceof Operator) {
                stack.add(new CalculateFrame((Operator)obj));
            } else {
                result = (Integer)obj;
                

                while (!stack.isEmpty()) {
                    stack.peek().setArg(result);
                    if (!stack.peek().canCalculate()) break;

                    result = stack.pop().calculate();
                }
            }
        }

        return result;
    }

    private boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    public static void main(String[] args) {
        Q227 q227 = new Q227();
        System.out.println(q227.calculate("3+4 / 2*2 * 2"));
        System.out.println(q227.calculate("3+4"));
        System.out.println(q227.calculate("34"));
        System.out.println(q227.calculate("0"));
    }
}
