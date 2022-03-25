package per.eicho.demo.leetcode.q401_500;

/**
 * <p>402. Remove K Digits 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/remove-k-digits/">402. Remove K Digits</a>
 */
public final class Q402 {
    public String removeKdigits(String num, int k) {
        // 1. 1 <= k <= num.length <= 10^5
        // 2. num consists of only digits.
        // 3. num does not have any leading zeros except for the zero itself.
        if (k == num.length()) return "0";
        final StringBuilder monoStack = new StringBuilder();

        int length = num.length();
        for (int i = 0; i < length; ++i) {
            final char digit = num.charAt(i);
            while (monoStack.length() != 0 && k > 0 && peekLast(monoStack) > digit) {
                pop(monoStack);
                k--;
            }

            monoStack.append(digit);
        }

        pop(monoStack, k);
        
        // processing leading-zero.
        int p = 0;
        while (p < monoStack.length() && monoStack.charAt(p) == '0') p++;
        monoStack.delete(0, p);

        return monoStack.length() == 0 ? "0" : monoStack.toString();
    }

    private char peekLast(StringBuilder monoStack) {
        return monoStack.charAt(monoStack.length() - 1);
    }
    
    private void pop(StringBuilder monoStack) {
        pop(monoStack, 1);
    }

    private void pop(StringBuilder monoStack, int time) {
        if (time == 0) return;
        if (monoStack.length() <= time) {
            monoStack.setLength(0);
            return;
        }

        monoStack.delete(monoStack.length() - time, monoStack.length());
    }
}
