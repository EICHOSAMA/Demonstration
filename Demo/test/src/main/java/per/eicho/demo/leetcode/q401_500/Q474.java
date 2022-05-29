package per.eicho.demo.leetcode.q401_500;

/**
 * <p>474. Ones and Zeroes 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/ones-and-zeroes/">
 *   474. Ones and Zeroes</a>
 */
public final class Q474 {
    public int findMaxForm(String[] strs, int m, int n) {
        // 1. 1 <= strs.length <= 600
        // 2. 1 <= strs[i].length <= 600
        // 3. strs[i] consists only of digits '0' and '1'
        // 4. 1 <= m, n <= 100
        return findMaxForm(convert(strs), m, n);
    }

    private int[][] convert(String[] strs) {
        final int n = strs.length;
        final int[][] countInfo = new int[n][2];
        for (int i = 0; i < n; i++) {
            final String str = strs[i];
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '0') {
                    countInfo[i][0]++;
                } else {
                    countInfo[i][1]++;
                }
            }
        }
        return countInfo;
    }

    private int findMaxForm(int[][] strs, int m, int n) {
        final int len = strs.length;
        final int[][][] f = new int[len + 1][m + 1][n + 1];
        for (int i = 1; i <= len; i++) {
            final int[] str = strs[i - 1];
            final int count0 = str[0];
            final int count1 = str[1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    f[i][j][k] = f[i - 1][j][k];
                    if (j >= count0 && k >= count1) {
                        f[i][j][k] = Math.max(f[i][j][k], f[i - 1][j - count0][k - count1] + 1);
                    }
                }
            }
        }
        return f[len][m][n];
    }

    public static void main(String[] args) {
        Q474 q474 = new Q474();
        // System.out.println(q474.findMaxForm(new String[]{"10","0001","111001","1","0"}, 5, 3));
        // System.out.println(q474.findMaxForm(new String[]{"10","0","1"}, 1, 1));
        System.out.println(q474.findMaxForm(new String[]{"0","0","1", "1"}, 2, 2));
        
    }
}
