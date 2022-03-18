package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1323. Maximum 69 Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-69-number/">
 *   1323. Maximum 69 Number</a>
 */
public final class Q1323 {
    public int maximum69Number (int num) {
        // 1. 1 <= num <= 10^4
        // 2. num consists of only 6 and 9 digits.
        int div = 1000;
        while (div != 0) {
            final int digit = (num / div) % 10;
            if (digit == 0 || digit == 9) {
                div /= 10;
                continue;
            }

            num -= (div * digit);
            num += (div * 9);
            break;
        }

        return num;
    }
}
