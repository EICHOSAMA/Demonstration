package per.eicho.demo.leetcode.q1101_1200;

/**
 * <p>1139. Largest 1-Bordered Square 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/largest-1-bordered-square/">
 *   1139. Largest 1-Bordered Square</a>
 */
public final class Q1139 {
    public int largest1BorderedSquare(int[][] grid) {
        // 1. 1 <= grid.length <= 100
        // 2. 1 <= grid[0].length <= 100
        // 3. grid[i][j] is 0 or 1
        final int m = grid.length;
        final int n = grid[0].length;

        final int[][] sumRow = new int[m][n];
        final int[][] sumCol = new int[n][m];
        for (int i = 0; i < m; i++) {
            final int[] row = sumRow[i];
            int rowSum = 0;

            for (int j = 0; j < n; j++) {
                final int[] col = sumCol[j];
                
                final int val = grid[i][j];

                rowSum += val;
                row[j] = rowSum;

                col[i] = val + (i > 0 ? col[i - 1] : 0);
            }
        }

        int len = 0;
        for (int i = 1, bound = Math.min(m, n); i <= bound; i++) {
            if (isValid(grid, sumRow, sumCol, i)) len = i;
        }
        return len * len;
    }

    private boolean isValid(int[][] grid, int[][] sumRow, int[][] sumCol, int len) {
        final int m = grid.length;
        final int n = grid[0].length;

        for (int i = 0, i2 = len - 1; i2 < m; i++, i2++) {
            for (int j = 0, j2 = len - 1; j2 < n; j++, j2++) {
                if (sum(sumRow[i], j, j2) != len) continue;
                if (sum(sumRow[i2], j, j2) != len) continue;
                if (sum(sumCol[j], i, i2) != len) continue;
                if (sum(sumCol[j2], i, i2) != len) continue;
                return true;
            }
        }
        return false;
    }

    private int sum(int[] sum, int l, int r) {
        return sum[r] - (l > 0 ? sum[l - 1] : 0);
    }

    public static void main(String[] args) {
        Q1139 q1139 = new Q1139();
        System.out.println(q1139.largest1BorderedSquare(new int[][]{
            new int[]{1,1,1},
            new int[]{1,0,1},
            new int[]{1,1,1}
        }));
    }
}
