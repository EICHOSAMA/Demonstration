package per.eicho.demo.leetcode.q801_900;

import java.util.Arrays;

/**
 * <p>887. Super Egg Drop 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/super-egg-drop/">
 *   887. Super Egg Drop</a>
 */
public final class Q887 {

    private int[][] f;

    public int superEggDrop(int k, int n) {
        // 1. 1 <= k <= 100
        // 2. 1 <= n <= 10^4
        f = new int[k][n + 1];
        for (int[] row : f) {
            Arrays.fill(row, -1);
            row[0] = 0;
            row[1] = 1;
        }
        for (int i = 0; i <= n; i++) f[0][i] = i;
        return dp(k - 1, n);
    }

    private int dp(int k, int n) {
        if (f[k][n] != -1) return f[k][n];
        int l = 1, r = n;
        int result = n;
        while (l < r) {
            final int mid = (l + r + 1) / 2;

            final int fL = dp(k - 1, mid - 1);
            final int fR = dp(k, n - mid);
            
            if (fL == fR) {
                result = Math.min(result, fL + 1);
                break;
            }

            if (fL < fR) {
                l = mid;
                result = Math.min(result, fR + 1);
            } else {
                // fL > fR 
                r = mid - 1;
                result = Math.min(result, fL + 1);
            }
        }
        
        return f[k][n] = result;
    }

    public static void main(String[] args) {
        Q887 q887 = new Q887();
        System.out.println(q887.superEggDrop(2, 10_000)); // 141

        System.out.println(q887.superEggDrop(3, 14)); // 4
        System.out.println(q887.superEggDrop(7, 10_000));  // 15
        System.out.println(q887.superEggDrop(8, 10_000));  // 14
        System.out.println(q887.superEggDrop(9, 10_000));  // 14
        System.out.println(q887.superEggDrop(10, 10_000)); // 14
        System.out.println(q887.superEggDrop(11, 10_000)); // 14
        System.out.println(q887.superEggDrop(12, 10_000)); // 14
        System.out.println(q887.superEggDrop(13, 10_000)); // 14
        System.out.println(q887.superEggDrop(14, 10_000)); // 14
        System.out.println(q887.superEggDrop(15, 10_000)); // 14
        // 2^14 = 16,384
        // 2^10 = 1024
        // 2^7  = 128
    }
}
