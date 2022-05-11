package per.eicho.demo.leetcode.q1601_1700;

import java.util.Arrays;

/**
 * <p>1652. Defuse the Bomb 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/defuse-the-bomb/">
 *   1652. Defuse the Bomb</a>
 */
public final class Q1652 {
    public int[] decrypt(int[] code, int k) {
        // 1. n == code.length
        // 2. 1 <= n <= 100
        // 3. 1 <= code[i] <= 100
        // 4. -(n - 1) <= k <= n - 1
        if (k == 0) {
            Arrays.fill(code, 0);
            return code;
        }

        // sliding window
        final int n = code.length;
        final int[] result = new int[n];

        int l, r, cnt, sum;
        if (k > 0) {
            l = 1; r = l; cnt = 1; sum = code[l];
        } else {
            k = -k;
            l = n - k; r = l; cnt = 1; sum = code[l];
        }

        while (cnt != k) {
            r = next(r, n);
            sum += code[r];
            cnt++;
        }

        result[0] = sum;

        int p = 0;
        while (++p < n) {
            sum -= code[l];
            l = next(l, n);
            r = next(r, n);
            sum += code[r];
            result[p] = sum;
        }

        return result;
    }

    private int next(int idx, int size) {
        return ++idx == size ? 0 : idx;
    }
}
