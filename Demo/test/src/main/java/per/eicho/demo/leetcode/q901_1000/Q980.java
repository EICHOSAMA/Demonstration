package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>980. Unique Paths III 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/unique-paths-iii/">
 *   980. Unique Paths III</a>
 */
public final class Q980 {

    boolean[][] marks;
    int m;
    int n;
    int[][] grid;

    int startX;
    int startY;

    int endX;
    int endY;

    private static final int STARTING = 1;
    private static final int ENDING = 2;
    private static final int BLANK = 0;
    private static final int OBSTACLE = -1;
    int count = 0;

    private static final int[][] directions = new int[][]{
        new int[]{1, 0}, new int[]{0, 1},
        new int[]{-1, 0}, new int[]{0, -1}
    };    

    public int uniquePathsIII(int[][] grid) {
        // 1. m == grid.length
        // 2. n == grid[i].length
        // 3. 1 <= m, n <= 20
        // 4. 1 <= m * n <= 20
        // 5. -1 <= grid[i][j] <= 2
        // 6. There is exactly one starting cell and one ending cell.
        m = grid.length;
        n = grid[0].length;
        marks = new boolean[m][n];
        this.grid = grid;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                final int val = grid[i][j];
                if (val == BLANK) {
                    count++;
                } else if (val == STARTING) {
                    startX = i;
                    startY = j;
                    grid[i][j] = OBSTACLE;
                    marks[i][j] = true;
                } else if (val == ENDING) {
                    endX = i;
                    endY = j;
                    grid[i][j] = BLANK;
                    count++;
                } else {
                    marks[i][j] = true;
                }
            }
        }

        return dfs(startX, startY);
    }

    private int dfs(int x, int y) {
        if (x == endX && y == endY) return count == 0 ? 1 : 0;

        int numOfPath = 0;
        for (int[] direction : directions) {
            final int nx = x + direction[0];
            if (nx < 0 || nx >= m) continue;
            final int ny = y + direction[1];
            if (ny < 0 || ny >= n) continue;
            if (marks[nx][ny]) continue;

            marks[nx][ny] = true;
            count--;
            numOfPath += dfs(nx, ny);
            count++;
            marks[nx][ny] = false;
        }

        return numOfPath;
    }
}
