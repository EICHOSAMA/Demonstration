package per.eicho.demo.leetcode.q601_700;

/**
 * <p>688. Knight Probability in Chessboard 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/knight-probability-in-chessboard/">
 *   688. Knight Probability in Chessboard</a>
 */
public final class Q688 {

    final int[][] directions = new int[][] {
        new int[]{-1, -2}, new int[]{-2, -1},
        new int[]{-2,  1}, new int[]{-1,  2},
        new int[]{ 1,  2}, new int[]{ 2,  1},
        new int[]{ 2, -1}, new int[]{ 1, -2}
    };

    double[][][] memo;
    int n;

    public double knightProbability(int n, int k, int row, int column) {
        // 1. 1 <= n <= 25
        // 2. 0 <= k <= 100
        // 3. 0 <= row, column <= n        
        memo = new double[n][n][k + 1];
        this.n = n;
        return search(row, column, k);
    }

    private double search(int r, int c, int k) {
        if (r < 0 || r >= n || c < 0 || c >= n) return 0.0d;
        if (k == 0) return 1.0d;
        if (memo[r][c][k] != 0.0d) return memo[r][c][k];

        double probability = 0.0d;
        for (int[] direction : directions) {
            final int nr = r + direction[0];
            final int nc = c + direction[1];
            probability += search(nr, nc, k - 1);
        }

        return memo[r][c][k] = probability / 8.0d;
    }
}
