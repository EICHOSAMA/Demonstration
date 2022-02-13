package per.eicho.demo.leetcode.q001_100;

/**
 * <p>43. Multiply Strings 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/multiply-strings/">43. Multiply Strings</a>
 */
public final class Q43 {
    private static final class StringHelper {
        private StringHelper() {};

        public static void reverseString(char[] s) {
            for (int i = 0; i < s.length / 2; i++) {
                s[i] ^= s[s.length - 1 - i];
                s[s.length - 1 - i] ^= s[i];
                s[i] ^=s[s.length - 1 - i];
            }
        }

        public static String reverseString(String target) {
            char[] result = target.toCharArray();
            reverseString(result);
            return new String(result);
        }
    }

    
    public String multiply(String num1, String num2) {
        // 1. 1 <= num1.length, num2.length <= 200
        // 2. num1 and num2 consist of digits only.
        // 3. Both num1 and num2 do not contain any leading zero, except the number 0 itself.        
        num1 = StringHelper.reverseString(num1);
        num2 = StringHelper.reverseString(num2);

        int[] result = new int[num1.length() + num2.length() + 3];

        for (int i = 0; i < num1.length(); i++) {
            int digit1 = num1.charAt(i) - '0';
            int digit2;
            int carry = 0;
            int j;

            for (j = 0; j < num2.length(); j++) {
                digit2 = num2.charAt(j) - '0';
                result[i+j] += (digit1 * digit2) + carry;
                carry = result[i+j] / 10;
                result[i+j] %= 10;
            }

            while (carry != 0) {
                result[i+j] += carry;
                carry = result[i+j] / 10;
                result[i+j] %= 10;
                j++;
            }

        }

        // remove leading zero.
        int maxIndex = num1.length() + num2.length() + 2;
        while (maxIndex >= 0 && result[maxIndex] == 0) {
            maxIndex--;
        }

        StringBuilder sb = new StringBuilder();
        while (maxIndex >= 0) {
            sb.append(result[maxIndex--]);
        }

        return sb.length() == 0? "0" : sb.toString();
    }
}
