package per.eicho.demo.leetcode.q001_100;

/**
 * <p>8. String to Integer (atoi) 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/string-to-integer-atoi/">8. String to Integer (atoi)</a>
 */
public final class Q8 {

    private static char WHITE_SPACE = ' ';

    public int myAtoi(String str) {
        if (str == null ||
            str.length() == 0)
            return 0;

        final int len = str.length();

        // 1. Skip White Space.
        int startIndex = 0;
        char startChar = str.charAt(startIndex);
        while (startIndex < len && (startChar = str.charAt(startIndex)) == WHITE_SPACE) {
            startIndex++;
        }

        if (startIndex == len)
            return 0; // full of white space.

        // 2. Judge if there is any sign symbol.
        boolean isNegative = false;
        if (startChar == '-') {
            isNegative = true;
            startIndex++;
        } else if (startChar == '+') {
            isNegative = false;
            startIndex++;
        }

        // 3. construct number.
        long number = 0;
        int digits = 0;
        while (startIndex < len) {
            startChar = str.charAt(startIndex++);
            if (startChar < '0' || startChar > '9')
                break;


            if (digits < 12) {
                // overflow
                number = number * 10L + (startChar - 48L);
                if (number != 0L)
                    digits++; //start
            }
        }

        // 4. not any numeric digit.
        if (digits == 0)
            return 0;

        // 5. add sign.
        if (isNegative)
            number *= -1L;

        // 5. check if over flow.
        if (number > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (number < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int)number;
    }    
}
