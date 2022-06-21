package per.eicho.demo.leetcode.q801_900;

/**
 * <p>808. Soup Servings 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/soup-servings/">
 *   808. Soup Servings</a>
 */
public final class Q808 {
    
    private static final int[][] ops = new int[][]{
        new int[]{-4,  0},
        new int[]{-3, -1},
        new int[]{-2, -2},
        new int[]{-1, -3}
    };

    
    public double soupServings(int n) {
        // 1. 0 <= n <= 10^9
        n = n / 25 + (n % 25 == 0 ? 0 : 1);
        if (n == 0) return 0.5d;
        if (n >= 500) return 1.0d;
        final double[][] f = new double[n + 1][n + 1];        
        f[n][n] = 1.0d;

        
        double result = 0.0d;
        for (int i = n; i >= 1; i--) {
            for (int j = n; j >= 1; j--) {
                final double fij = f[i][j];
                for (int[] op : ops) { 
                    final int ni = Math.max(i + op[0], 0); 
                    final int nj = Math.max(j + op[1], 0);
                    final double rate = fij / 4.0d;
                    f[ni][nj] += rate;
                    if (ni == 0) result += rate / (nj == 0 ? 2.0d : 1.0d);
                }
            }
        }
        return result;
    }
}
