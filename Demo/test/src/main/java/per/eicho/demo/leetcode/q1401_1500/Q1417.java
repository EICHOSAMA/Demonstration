package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1417. Reformat The String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reformat-the-string/">
 *   1417. Reformat The String</a>
 */
public final class Q1417 {
    public String reformat(String s) {
        // 1. 1 <= s.length <= 500
        // 2. s consists of only lowercase English letters and/or digits.        
        final int n = s.length();
        int digitCount = 0;
        int letterCount = 0;
        for (int i = 0; i < n; i++) {
            final char ch = s.charAt(i);

            if ('0' <= ch && ch <= '9') digitCount++;
        }
        letterCount = n - digitCount;

        if (Math.abs(letterCount - digitCount) > 1) return "";

        final char[] result = new char[n];
        int dp = 0, lp = 0;
        if (letterCount >= digitCount) {
            dp = 1;
        } else {
            lp = 1;
        }

        for (int i = 0; i < n; i++) {
            final char ch = s.charAt(i);

            if ('0' <= ch && ch <= '9') {
                // digit
                result[dp] = ch;
                dp += 2;
            } else {
                // letter
                result[lp] = ch;
                lp += 2;
            }
        }

        return new String(result);
    }
}
