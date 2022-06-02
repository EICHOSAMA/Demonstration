package per.eicho.demo.leetcode.q501_600;

/**
 * <p>537. Complex Number Multiplication 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/complex-number-multiplication/">
 *   537. Complex Number Multiplication</a>
 */
public final class Q537 {

    final char[] chars = new char[]{'+', 'i'};

    public String complexNumberMultiply(String num1, String num2) {
        // num1 and num2 are valid complex numbers.
        final int[] n1 = parse(num1);
        final int[] n2 = parse(num2);
        return format(new int[]{n1[0] * n2[0] - n1[1] * n2[1], n1[0] * n2[1] + n2[0] * n1[1]});
    }

    private String format(int[] num) {
        final StringBuilder sb = new StringBuilder();
        sb.append(num[0]).append(chars[0]).append(num[1]).append(chars[1]);
        return sb.toString();
    }

    private int[] parse(String num) {
        // 1. A complex number can be represented as a string on the form "real+imaginaryi" where:
        // 2. real is the real part and is an integer in the range [-100, 100].
        // 3. imaginary is the imaginary part and is an integer in the range [-100, 100].
        // 4.i^2 == -1.
        // i.e. -1+-1i
        final int[] result = new int[2];

        int p = 0;
        int n = 0;
        int p2 = 0;
        int cursor = 0;
        int symbol = 1;
        while (p < 2) {
            final char ch = num.charAt(cursor++);
            if (ch == chars[p]) {
                p++;
                result[p2++] = n * symbol;

                symbol = 1;
                n = 0;
                continue;
            }

            if (ch == '-') {
                symbol = -1;
            } else {
                n = n * 10 + (ch - '0');
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q537 q537 = new Q537();
        System.out.println(q537.complexNumberMultiply("0+-1i", "-1+0i"));
    }
}
