package per.eicho.demo.leetcode.q501_600;

/**
 * <p>592. Fraction Addition and Subtraction 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/fraction-addition-and-subtraction/">
 *   592. Fraction Addition and Subtraction</a>
 */
public final class Q592 {

    final static int TEN_FACTORIAL = 3628800; // 10!

    public String fractionAddition(String expression) {
        // 1. The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
        // 2. Each fraction (input and output) has the format ±numerator/denominator. 
        //    If the first input fraction or the output is positive, then '+' will be omitted.
        // 3. The input only contains valid irreducible fractions, where the numerator and denominator 
        //    of each fraction will always be in the range [1, 10]. If the denominator is 1, 
        //    it means this fraction is actually an integer in a fraction format defined above.
        // 4. The number of given fractions will be in the range [1, 10].
        // 5. The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int.        
        final int[] numerators = new int[10 + 1]; // [0, 10], 0 will not be used
        final int n = expression.length();
        int intPart = 0;
        
        boolean positive = true;
        final int[] workspace = new int[2]; // [0]: numerator, [1]: denominator 
        for (int i = 0; i < n; i++) {
            final char ch = expression.charAt(i);

            if (ch == '-') {
                positive ^= true; // true ⇒ false, false ⇒ true
            } else if (isDigit(ch)) {
                int p = 0;
                int num = 0;
                while (p < 2) {
                    if (i < n && expression.charAt(i) == '/') {
                        i++;
                        continue;
                    }

                    while (i < n && isDigit(expression.charAt(i))) {
                        num = num * 10 + (expression.charAt(i++) - '0');
                    }

                    workspace[p++] = num * (positive ? 1 : -1); // save to worksape.
                    num = 0; // reset
                    positive = true;
                }
                i--;

                final int numerator = workspace[0] * (positive ? 1 : -1);
                final int denominator = workspace[1]; 
                numerators[denominator] += numerator;
                if ((numerators[denominator] / denominator) != 0) {
                    intPart += (numerators[denominator] / denominator);
                    numerators[denominator] %= denominator;
                }
                positive = true;
                num = 0;
            }
        }

        final long denominator = TEN_FACTORIAL;
        long numerator = 0;
        for (int i = 1; i <= 10; i++) {
            if (numerators[i] == 0) continue;
            numerator += numerators[i] * (TEN_FACTORIAL / i);
        }
        numerator += ((long)intPart * denominator);

        positive = true;
        if (numerator < 0) {
            numerator *= -1;
            positive = false;
        }
        final long gcd = gcd(numerator, denominator);

        final StringBuilder sb = new StringBuilder();
        if (!positive) sb.append('-');
        sb.append(numerator / gcd).append('/').append(denominator / gcd);
        return sb.toString();
    }

    private long gcd(long num1, long num2) {
        long temp;
        while (num2 != 0) {
            temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }

    private boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    public static void main(String[] args) {
        Q592 q592 = new Q592();
        System.out.println(q592.fractionAddition("-1/2+1/2"));
    }
}
