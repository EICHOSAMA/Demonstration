package per.eicho.demo.leetcode.q601_700;

import java.util.Arrays;

/**
 * <p>672. Bulb Switcher II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/bulb-switcher-ii/">
 *   672. Bulb Switcher II</a>
 */
public final class Q672 {
    public int flipLights(int n, int presses) {
        // 1. 1 <= n <= 1000
        // 2. 0 <= presses <= 1000        
        n = n <= 6 ? n : 6 + (n % 6);
        final int statusCount = pow(2, n); // 2^n
        final boolean[][] f = new boolean[2][statusCount]; 
        f[0][statusCount - 1] = true;

        final int mask = statusCount - 1; // 2^n - 1 = 0b11111...111 
        for (int i = 0; i < presses; i++) {
            final int current = i % 2;
            final int next = (i + 1) % 2;

            Arrays.fill(f[next], false);
            for (int status = 0; status < statusCount; status++) {
                if (f[current][status] == false) continue;

                // Button 1: Flips the status of all the bulbs.
                f[next][reverseAll(status) & mask] = true;
                // Button 2: Flips the status of all the bulbs with even labels (i.e., 2, 4, ...).
                f[next][reverseEven(status) & mask] = true;
                // Button 3: Flips the status of all the bulbs with odd labels (i.e., 1, 3, ...).
                f[next][reverseOdd(status) & mask] = true;
                // Button 4: Flips the status of all the bulbs with a label j = 3k + 1 where k = 0, 1, 2, ... (i.e., 1, 4, 7, 10, ...).
                f[next][reverse3k(status) & mask] = true;
            }
        }

        int result = 0;
        int current = presses % 2;
        for (int i = 0; i < statusCount; i++) if (f[current][i]) result++;
        return result;
    }

    private int pow(int a, int b) {
        if (b == 0) return 1;
        final int half = pow(a, b / 2);
        return half * half * (b % 2 == 0 ? 1 : a);
    }

    private int reverseAll(int n) {
        return n ^ 0b11111111_11111111_11111111_11111111;
    }

    private int reverseOdd(int n) {
        return n ^ 0b01010101_01010101_01010101_01010101;
    }

    private int reverseEven(int n) {
        return n ^ 0b10101010_10101010_10101010_10101010;
    }

    private int reverse3k(int n) {
        return n ^ 0b01001001_00100100_10010010_01001001;
    }
}
