package per.eicho.demo.leetcode.q1301_1400;

import java.util.Arrays;

/**
 * <p>1363. Largest Multiple of Three 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/largest-multiple-of-three/">
 *   1363. Largest Multiple of Three</a>
 */
public final class Q1363 {
    public String largestMultipleOfThree(int[] digits) {
        // 1. 1 <= digits.length <= 10^4
        // 2. 0 <= digits[i] <= 9
        Arrays.sort(digits);
        int sum = 0;
        final int[] counts = new int[10];
        final int[] remainCounts = new int[3];
        for (int digit : digits) {
            sum += digit;
            counts[digit]++;
            remainCounts[digit % 3]++;
        }

        if (!check(counts, sum % 3)) return "";
        final StringBuilder sb = new StringBuilder(digits.length);
        int p = 9;
        while (p >= 0) {
            for (int i = 0, time = counts[p]; i < time; i++) sb.append(p);
            p--;
        }

        if (sb.length() == 0) return "";
        return sb.charAt(0) == '0' ? "0" : sb.toString();
    }

    private boolean check(int[] counts, int remain) {
        if (remain == 0) return true;
        // 1 -> 1 x 1 then 2 x 2
        // 2 -> 2 x 1 then 1 x 2   
        for (int i = remain; i <= 9; i += 3) {
            // 1, 4, 7
            // 2, 5, 8
            if (counts[i] >= 1) {
                counts[i]--;
                return true;
            }
        }

        remain ^= 0b11; // 0b01 -> 0b10 , 0b10 -> 0b01
        for (int i = remain, want = 2; i <= 9 && want > 0; i += 3) {
            while (counts[i] > 0 && want > 0) {
                counts[i]--;
                want--;
            }

            if (want == 0) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Q1363 q1363 = new Q1363();
        System.out.println(q1363.largestMultipleOfThree(new int[]{8,6,7,1,0}));
        System.out.println(q1363.largestMultipleOfThree(new int[]{1,1,1}));
        System.out.println(q1363.largestMultipleOfThree(new int[]{1}));
    }
}
