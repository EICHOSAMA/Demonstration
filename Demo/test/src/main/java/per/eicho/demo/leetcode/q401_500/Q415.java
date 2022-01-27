package per.eicho.demo.leetcode.q401_500;

/**
 * <p>415. Add Strings 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/add-strings/">415. Add Strings</a>
 */
public final class Q415 {
    public String addStrings(String num1, String num2) {
        // 1. 1 <= num1.length, num2.length <= 10^4
        // 2. num1 and num2 consist of only digits.
        // 3. num1 and num2 don't have any leading zeros except for the zero itself.
        if (num1.length() < num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        final int len1 = num1.length();
        final int len2 = num2.length();

        StringBuilder sbRes = new StringBuilder();

        StringBuilder sb1 = new StringBuilder(num1).reverse();
        StringBuilder sb2 = new StringBuilder(num2).reverse();
        int carry = 0;
        for (int i = 0; i < len1; i++) {
            int current = carry + (sb1.charAt(i) - '0');
            if (i < len2) current += (sb2.charAt(i) - '0');
            carry = current / 10;
            sbRes.append(current % 10);
        }
        if (carry != 0) sbRes.append(carry);

        return sbRes.reverse().toString();
    }
}
