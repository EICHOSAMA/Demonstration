package per.eicho.demo.algorithm.math.expression;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

import per.eicho.demo.leetcode.q101_200.Q150;

/**
 * <p>表达式分析：逆波兰表示法</p>
 * 
 * <pre>
 *  本Sample类将分析给定的数学表达式，并将其解析为逆波兰表示法
 *   
 *  <b>逆波兰表示法</b>，英：Reverse Polish notation，略：RPN。
 *  也被称为<b>后缀表示法</b>, 英：postfix notation。
 * </pre>
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Reverse_Polish_notation">逆波兰表示法（后缀表示法）</a>
 * @see <a href="https://en.wikipedia.org/wiki/Infix_notation">波兰表示法（前缀表示法）</a>
 * 
 * @see {@link Q150 150. Evaluate Reverse Polish Notation}
 */
final class RPNSample {
    
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

    private static enum Operator {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE;

        int calculate(int arg1, int arg2) {
            switch (this) {
                case PLUS: return arg1 + arg2;
                case MINUS: return arg1 - arg2;
                case MULTIPLY : return arg1 * arg2;
                default: return arg1 / arg2;
            }
        }

        static Operator toOperator(String str) {
            if (str == null || str.length() != 1) return null;
            switch ((char)str.charAt(0)) {
                case '+': return PLUS;
                case '-': return MINUS;
                case '*': return MULTIPLY;
                case '/': return DIVIDE;
                default: return null;
            }
        }

        static boolean isOperator(String str) {
            return toOperator(str) != null;
        }
    }

    /**
     * <p>计算逆波兰表达式的值</p>
     * 
     * <pre>
     *  以字符串数组的形式给定逆波兰表达式，求得其最终计算的结果。
     *  
     *  字符串数组中的任一元素是以下之一：
     *    1. 数字的字符串表示
     *    2. 运算符(+-*)的字符串表示。其中除号是"/"。
     * </pre>
     * 
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        if (tokens.length == 1) return Integer.valueOf(tokens[0]);
        // 1. 1 <= tokens.length <= 10^4
        // 2. tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
        final LinkedList<String> tokenList = new LinkedList<String>();
        Collections.addAll(tokenList, tokens);

        final Stack<CalculateFrame> stack = new Stack<>();
        int result = 0;
        while (tokenList.size() > 0) {
            final String input = tokenList.pollLast();

            // case1: operator, add calculate frame.
            if (Operator.isOperator(input)) {
                stack.add(new CalculateFrame(Operator.toOperator(input)));
                continue;
            } 

            // case2: number, fill calculate frame args.
            final Integer arg = Integer.valueOf(input);
            CalculateFrame calculateFrame = stack.peek();
            calculateFrame.setArg(arg);

            while (calculateFrame.canCalculate()) {
                final Integer calculateResult = calculateFrame.calculate();

                stack.pop();
                if (stack.isEmpty()) {
                    result = calculateResult;
                    break;
                }
                calculateFrame = stack.peek();
                calculateFrame.setArg(calculateResult);
            }
        }
        return result;
    }
}
