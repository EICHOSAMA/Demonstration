package per.eicho.demo.leetcode.q501_600;

/**
 * <p>576. Out of Boundary Paths 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/out-of-boundary-paths/">
 *   576. Out of Boundary Paths</a>
 */
public final class Q576 {
    
    int[][][] memo;
    int m;
    int n;
    private final static int MODULO = 1_000_000_000 + 7;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        // 1. 1 <= m, n <= 50
        // 2. 0 <= maxMove <= 50
        // 3. 0 <= startRow < m
        // 4. 0 <= startColumn < n
        if (maxMove == 0) return 0;
        this.m = m;
        this.n = n;
        // 2. 1 <= maxMove <= 50
        memo = new int[m][n][maxMove + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 2; k <= maxMove; k++) {
                    memo[i][j][k] = -1;
                }

                if (i - 1 < 0) memo[i][j][1]++;
                if (i + 1 >= m) memo[i][j][1]++;
                if (j - 1 < 0) memo[i][j][1]++;
                if (j + 1 >= n) memo[i][j][1]++;
            }
        }
        return search(startRow, startColumn, maxMove);
    }

    private int search(int x, int y, int remainMoves) {
        if (x < 0 || x >= m || y < 0 || y >= n) return 1;
        if (memo[x][y][remainMoves] != -1) return memo[x][y][remainMoves];

        long path = 0;
        path += search(x - 1, y, remainMoves - 1);
        path += search(x + 1, y, remainMoves - 1);
        path += search(x, y - 1, remainMoves - 1);
        path += search(x, y + 1, remainMoves - 1);
        return memo[x][y][remainMoves] = (int)(path % MODULO);
    }

    public static void main(String[] args) {
        Q576 q576 = new Q576();
        System.out.println(q576.findPaths(50, 50, 50, 0, 0));
    }
}
