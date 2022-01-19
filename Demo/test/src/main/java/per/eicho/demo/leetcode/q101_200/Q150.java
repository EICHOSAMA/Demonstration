package per.eicho.demo.leetcode.q101_200;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 * <p>150. Evaluate Reverse Polish Notation 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/evaluate-reverse-polish-notation/">150. Evaluate Reverse Polish Notation</a>
 */
public final class Q150 {

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
        PLUS('+'),
        MINUS('-'),
        MULTIPLY('*'),
        DIVIDE('/');

        @SuppressWarnings("unused")
        final char opSymbol;

        Operator(char symbol) {
            this.opSymbol = symbol;
        }

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

    public static void main(String[] args) {
        Q150 q150 = new Q150();
        System.out.println(q150.evalRPN(new String[]{"4","-2","/","2","-3","-","-"}));
    }
}
