package per.eicho.demo.leetcode.q1601_1700;

import java.util.Arrays;

/**
 * <p>1641. Count Sorted Vowel Strings 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-sorted-vowel-strings/">
 *   1641. Count Sorted Vowel Strings</a>
 */
public final class Q1641 {
    public int countVowelStrings(int n) {
        // 1. 1 <= n <= 50 
        final int[][] f = new int[n][5];
        Arrays.fill(f[0], 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k <= j; k++) {
                    f[i][j] += f[i - 1][k];
                }
            }
        }

        int result = 0;
        for (int count : f[n - 1]) {
            result += count;
        }
        return result;
    }

    public static void main(String[] args) {
        Q1641 q1641 = new Q1641();
        System.out.println(q1641.countVowelStrings(33));
    }
}
