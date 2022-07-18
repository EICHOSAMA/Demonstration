package per.eicho.demo.leetcode.q1101_1200;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * <p>1124. Longest Well-Performing Interval 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-well-performing-interval/">
 *   1124. Longest Well-Performing Interval</a>
 */
public final class Q1124 {
    public int longestWPI(int[] hours) {
        // 1. 1 <= hours.length <= 10^4
        // 2. 0 <= hours[i] <= 16
        final int n = hours.length;
        for (int i = 0; i < n; i++) {
            // A day is considered to be a tiring day 
            // if and only if the number of hours worked is (strictly) greater than 8.
            hours[i] = hours[i] > 8 ? 1 : 0;
        }

        // A well-performing interval is an interval of days 
        // for which the number of tiring days is strictly larger than the number of non-tiring days.
        int sum = 0;
        final int[] sums = new int[n];
        for (int i = 0; i < n; i++) sums[i] = sum += hours[i];
        if (sums[n - 1] == 0) return 0; 
        
        int result = 1;
        for (int p = 0, bound = n - 1 - result; p <= bound; p++) {
            int l = p + result, r = n - 1; // [l, R]
            while (r >= l) {
                final int count = countRange(sums, p, r);
                if (2 * count > r - p + 1) {
                    result = r - p + 1;
                    bound = n - 1 - result;
                    break;
                }
                r--;
            }
        }
        return result;
    }

    private int countRange(int[] sums, int l, int r) {
        return sums[r] - (l == 0 ? 0 : sums[l - 1]);
    }

    public static void main(String[] args) {
        Q1124 q1124 = new Q1124();
        
        int[] input = new int[10000];
        final Random random = new Random();
        for (int i = 0; i < input.length; i++) {
            input[i] = random.nextInt(16);
        }
        // int[] input = new int[]{6,9,9};

        System.out.println(LocalDateTime.now());
        System.out.println(q1124.longestWPI(input));
        System.out.println(LocalDateTime.now());
    }
}
