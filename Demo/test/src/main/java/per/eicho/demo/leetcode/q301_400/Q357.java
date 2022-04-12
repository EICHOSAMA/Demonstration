package per.eicho.demo.leetcode.q301_400;

/**
 * <p>357. Count Numbers with Unique Digits 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-numbers-with-unique-digits/">
 *   357. Count Numbers with Unique Digits</a>
 */
public class Q357 {
    public int countNumbersWithUniqueDigits(int n) {
        // 1. 0 <= n <= 8
        int result = 10;
        if (n == 0) return 1;
        if (n == 1) return result;
        if (n > 10) n = 10;

        int pi9 = 9; // P(1,9) = 9
        for (int i = 1; i < n; i++) {
            // P(i,9) = 9! / (9-i)!
            result += 9 * pi9;
            pi9 *= (9-i);// calculate next pi9.
        }

        return result;
    }
}
