package per.eicho.demo.leetcode.q001_100;

/**
 * <p>67. Add Binary 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/add-binary/">67. Add Binary</a>
 */
public final class Q67 {
    private static int OFFSET = -2 * '0' ;

    public String addBinary(String a, String b) {
        // 1. 1 <= a.length, b.length <= 104
        // 2. a and b consist only of '0' or '1' characters.
        // 3. Each string does not contain leading zeros except for the zero itself.

        int carry = 0, digit;
        int ia = a.length() - 1, ib = b.length() - 1;

        int ca, cb;
        StringBuilder sb = new StringBuilder(Math.max(a.length(), b.length()) + 2);
        while (ia >= 0 || ib >= 0 || carry != 0) {
            ca = ia < 0 ? '0': a.charAt(ia--);
            cb = ib < 0 ? '0': b.charAt(ib--);

            digit = OFFSET + ca + cb + carry;
            carry = digit / 2;
            digit %= 2;
            sb.append(digit);
        }

        return sb.reverse().toString();
    }
}
