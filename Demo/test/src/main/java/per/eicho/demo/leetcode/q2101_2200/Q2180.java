package per.eicho.demo.leetcode.q2101_2200;

/**
 * <p>2180. Count Integers With Even Digit Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-integers-with-even-digit-sum/">
 *   2180. Count Integers With Even Digit Sum</a>
 */
public final class Q2180 {
    public int countEven(int num) {
        // 1. 1 <= num <= 1000
        final int lastDigit = num % 10;
        final int l = num - lastDigit;
        final int r = num;
        int result = (num / 10) * 5 - 1; // exclude 0
        for (int i = Math.max(0, l); i <= r; i++) {
            if (check(i)) result++;
        }
        return result;
    }

    private boolean check(int num) {
        int sum = 0;
        while (num != 0) {
            sum += (num % 10);
            num /= 10;
        }
        return sum % 2 == 0;
    }
}
