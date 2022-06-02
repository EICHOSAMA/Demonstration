package per.eicho.demo.leetcode.q501_600;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>542. 01 Matrix 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/01-matrix/">
 *   542. 01 Matrix</a>
 */
public final class Q542 {

    final int[][] adjacentCells = new int[][]{
        new int[]{-1, 0},
        new int[]{1, 0},
        new int[]{0, -1},
        new int[]{0, 1}
    };

    public int[][] updateMatrix(int[][] mat) {
        // 1. m == mat.length
        // 2. n == mat[i].length
        // 3. 1 <= m, n <= 10^4
        // 4. 1 <= m * n <= 10^4
        // 5. mat[i][j] is either 0 or 1.
        // 6. There is at least one 0 in mat.        
        final int m = mat.length;
        final int n = mat[0].length;
        final int[][] result = new int[m][n];
        final boolean[][] mark = new boolean[m][n];
        final Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.add(new int[]{i , j});
                    mark[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            final int size = queue.size();
            int[] coordinate = queue.peek();
            final int layer = result[coordinate[0]][coordinate[1]];
            for (int i = 0; i < size; i++) {
                coordinate = queue.poll();

                for (int[] move : adjacentCells) {
                    final int ni = coordinate[0] + move[0];
                    if (ni < 0 || ni >= m) continue;
                    final int nj = coordinate[1] + move[1];
                    if (nj < 0 || nj >= n) continue;
    
                    if (!mark[ni][nj]) {
                        mark[ni][nj] = true;
                        result[ni][nj] = layer + 1; 
                        queue.add(new int[]{ni, nj});
                    }
                }
            }
        }
        return result;
    }
}
