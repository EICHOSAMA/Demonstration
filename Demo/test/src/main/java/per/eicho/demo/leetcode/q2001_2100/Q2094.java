package per.eicho.demo.leetcode.q2001_2100;

/**
 * <p>2094. Finding 3-Digit Even Numbers 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/finding-3-digit-even-numbers/">2094. Finding 3-Digit Even Numbers</a>
 */
public final class Q2094 {
    public int[] findEvenNumbers(int[] digits) {
        // 1. 3 <= digits.length <= 100
        // 2. 0 <= digits[i] <= 9
        final boolean[] mark = new boolean[1000]; // 0-999

        int count = 0;
        for (int i = 0; i < digits.length; i++) {
            final int dI = digits[i];
            if (dI == 0) continue;

            for (int j = 0; j < digits.length; j++) {
                if (j == i) continue;
                final int dJ = digits[j];

                for (int k = 0; k < digits.length; k++) {
                    if (k == j || k == i) continue;
                    final int dK = digits[k];
                    if (dK % 2 != 0) continue;

                    final int num = dI * 100 + dJ * 10 + dK;

                    if (!mark[num]) {
                        mark[num] = true;
                        count++;
                    }
                }
            }
        }

        final int[] result = new int[count];
        int p = 0;
        for (int i = 100; i < mark.length; i++) {
            if (!mark[i]) continue;
            result[p++] = i;
        }

        return result;
    }
}
