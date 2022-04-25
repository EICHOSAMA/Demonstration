package per.eicho.demo.leetcode.q2201_2300;

/**
 * <p>2243. Calculate Digit Sum of a String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/calculate-digit-sum-of-a-string/">
 *   2243. Calculate Digit Sum of a String</a>
 */
public final class Q2243 {
    public String digitSum(String s, int k) {
        // 1. 1 <= s.length <= 100
        // 2. 2 <= k <= 100
        // 3. s consists of digits only. 
        final int n = s.length();
        StringBuilder sb1 = new StringBuilder(n);
        StringBuilder sb2 = new StringBuilder(n);

        sb1.append(s);
        while (sb1.length() > k) {
            final int len = sb1.length();
            int p = 0;
            int cnt = 0;
            int sum = 0;
            while (p < len) {
                final int digit = sb1.charAt(p++) - '0';
                cnt++;
                sum += digit;
                if (cnt == k || p == len) {
                    sb2.append(sum);
                    cnt = 0;
                    sum = 0;
                }
            }

            sb1 = sb2;
            sb2 = new StringBuilder(sb1.length());
        }
        return sb1.toString();
    }
}
