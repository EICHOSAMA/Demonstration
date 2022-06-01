package per.eicho.demo.leetcode.q501_600;

import java.util.Arrays;

/**
 * <p>526. Beautiful Arrangement 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/beautiful-arrangement/">526. Beautiful Arrangement</a>
 */
public final class Q526 {
    
    int[] memo;
    int n;
    
    public int countArrangement(int n) {
        // 1 <= n <= 15
        memo = new int[power(2, n)];
        Arrays.fill(memo, -1);
        memo[memo.length - 1] = 1;
        this.n = n;
        return search(0, 1);
    }

    private int power(int a, int b) {
        if (b == 0) return 1;
        final int half = power(a, b / 2);
        return half * half * ((b % 2 == 1) ? a : 1);
    }

    private int search(final int status, int p) {
        if (memo[status] != -1) return memo[status];

        int result = 0;
        int bit = 0b1;
        for (int i = 0; i < n; i++, bit <<= 1) { // [0, n) ⇒ i + 1 → [1, n]
            if ((status & bit) != 0) continue; // label already been used.
            
            final int label = i + 1;
            if (label % p != 0 && p % label != 0) continue; // divisibility check
            result += search(status | bit, p + 1);
        }
        return memo[status] = result;
    }
}
