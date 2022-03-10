package per.eicho.demo.leetcode.q201_300;

/**
 * <p>289. Game of Life 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/game-of-life/">289. Game of Life</a>
 */
public final class Q289 {
    public void gameOfLife(int[][] board) {
        // 1. m == board.length
        // 2. n == board[i].length
        // 3. 1 <= m, n <= 25
        // 4. board[i][j] is 0 or 1
        // Ex1. Could you solve it in-place?
        final int m = board.length;
        final int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                processCoordinate(board, i, j);        
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;     
            }
        }
    }

    final int FIRST_BIT_MASK = 0b01;
    final int SECOND_BIT_MASK = 0b10;

    final int STATE_LIVE = 0b01;
    final int STATE_DIE = 0b00;

    final int[][] NEIGHBOR_OFFSETS = new int[][]{
        new int[]{-1 , -1},
        new int[]{-1, 0},
        new int[]{-1, 1},
        new int[]{0, -1},
        new int[]{0, 1},
        new int[]{1, -1},
        new int[]{1, 0},
        new int[]{1, 1}
    };

    private void processCoordinate(int[][] board, int i, int j) {
        // 1. (LN < 2 LIVE ⇒ DIE)         Any live cell with fewer than two live neighbors dies as if caused by under-population.
        // 2. (LV ∈ {2, 3} LIVE ⇒ LIVE)  Any live cell with two or three live neighbors lives on to the next generation.
        // 3. (LN > 2 LIVE ⇒ DIE)         Any live cell with more than three live neighbors dies, as if by over-population.
        // 4. (LV = 3 DIE ⇒ LIVE)         Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.        
        final int m = board.length;
        final int n = board[0].length;
        
        int ln = 0; // count live neighbors
        for (int[] offset : NEIGHBOR_OFFSETS) {
            final int ni = i + offset[0];
            if (ni < 0 || ni >= m) continue;
            final int nj = j + offset[1];
            if (nj < 0 || nj >= n) continue;

            if ((board[ni][nj] & FIRST_BIT_MASK) == STATE_LIVE) ln++;
        }

        if (board[i][j] == STATE_LIVE) {
            if (2 <= ln && ln <= 3) board[i][j] ^= 0b10; // TO LIVE
            return;
        }

        // DIE
        if (ln == 3) board[i][j] ^= 0b10; // TO LIVE
    }
}
