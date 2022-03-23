package per.eicho.demo.leetcode.q201_300;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>241. Different Ways to Add Parentheses 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/different-ways-to-add-parentheses/">
 *   241. Different Ways to Add Parentheses</a>
 */
@SuppressWarnings("unchecked")
public final class Q241 {

    private enum Operator {
        PLUS,
        MINUS,
        PRODUCT;

        static Operator parse(char symbol) {
            switch (symbol) {
                case '+': return Operator.PLUS;
                case '-': return Operator.MINUS;
                case '*': return Operator.PRODUCT;
                default: return null;
            }
        }

        int calc(int arg1, int arg2) {
            switch(this) {
                case PLUS: return arg1 + arg2;
                case MINUS: return arg1 - arg2;
                case PRODUCT: return arg1 * arg2;
                default: return -1; // impossible case.
            }
        }
    }

    public List<Integer> diffWaysToCompute(String expression) {
        // 1. 1 <= expression.length <= 20
        // 2. expression consists of digits and the operator '+', '-', and '*'.
        // 3. All the integer values in the input expression are in the range [0, 99].        

        // 1. analysis expression (str → tokens)
        int opCount = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (isDigit(expression.charAt(i))) continue;
            opCount++;
        }

        final int[] nums = new int[opCount + 1];
        final Operator[] ops = new Operator[opCount];
        int n = 0, o = 0;
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (!isDigit(ch)) {
                ops[o++] = Operator.parse(ch);
                continue;
            }

            int num = ch - '0';
            while (++i < expression.length() && isDigit(ch = expression.charAt(i))) {                
                num = num * 10 + (ch - '0');
            }
            nums[n++] = num;
            i--;
        }

        final List<Integer>[][] memos = new List[opCount + 1][opCount + 1];
        return diffWaysToCompute(nums, ops, 0, nums.length - 1, memos);
    }

    private boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    private List<Integer> diffWaysToCompute(int[] nums, Operator[] ops, int l, int r, List<Integer>[][] memos) {
        // [l, r]
        if (memos[l][r] != null) return memos[l][r];
        if (l == r) return memos[l][r] = Arrays.asList(nums[l]);

        final List<Integer> result = new LinkedList<>();
        for (int i = l; i < r; i++) {
            final List<Integer> left = diffWaysToCompute(nums, ops, l, i, memos);
            final List<Integer> right = diffWaysToCompute(nums, ops, i + 1, r, memos);
            final Operator op = ops[i];

            for (int numL : left) {
                for (int numR : right) {
                    result.add(op.calc(numL, numR));
                }
            }
        }
        return memos[l][r] = result;
    }

    public static void main(String[] args) {
        Q241 q241 = new Q241();
        List<Integer> output = q241.diffWaysToCompute("2*3-4*5");
        for (Integer num : output) {
            System.out.println(num);
        }
    }
}
