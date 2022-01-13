package per.eicho.demo.leetcode.q101_200;

/**
 * <p>130. Surrounded Regions 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/surrounded-regions/">130. Surrounded Regions</a>
 */
public final class Q130 {

    private static char X = 'X';
    private static char O = 'O';
    private static char G = 'G';

    private enum Direction {
        R(0, 1), B(1, 0), L(0, -1), U(-1, 0);

        final int x;
        final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Direction next() {
            switch (this) {
                case R: return Direction.B;
                case B: return Direction.L;
                case L: return Direction.U;
                default: return Direction.R;
            }
        }
    }

    public void solve(char[][] board) {
        // 1. m == board.length
        // 2. n == board[i].length
        // 3. 1 <= m, n <= 200
        // 4. board[i][j] is 'X' or 'O'.
        final int n = board.length;
        final int m = board[0].length;
        if (n == 1 || m == 1) return;

        // 1. Infect
        Direction direction = Direction.R;
        int i = 0, j = -1;
        int count = 2 * n + 2 * m - 4;
        while (count != 0) {

            // move
            i += direction.x;
            j += direction.y;
            if ((0 <= i && i < n) && (0 <= j && j < m)) {
                // if valid then process
                count--;
                infect(board, i, j);
            } else {
                // if invalid then change direction.
                // move back
                i -= direction.x;
                j -= direction.y;
                direction = direction.next();
                continue; 
            }
        }

        // 2. Replace 'G' ⇒ 'O', Replace 'O' ⇒ 'X'
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if (board[i][j] == X) continue;
                if (board[i][j] == O) {
                    board[i][j] = X;
                } else {
                    board[i][j] = O;
                }
            }
        }
    }

    private void infect(final char[][] board, int i, int j) {
        if (i < 0 || i >= board.length) return;
        if (j < 0 || j >= board[0].length) return;
        if (board[i][j] == X || board[i][j] == G) return;
        
        // assert board[i][j] == O;
        board[i][j] = G;
        infect(board, i + Direction.B.x, j + Direction.B.y);
        infect(board, i + Direction.L.x, j + Direction.L.y);
        infect(board, i + Direction.R.x, j + Direction.R.y);
        infect(board, i + Direction.U.x, j + Direction.U.y);
    }

    public static void main(String[] args) {
        Q130 q130 = new Q130();

        char[][] board = new char[][]{
            new char[]{'X', 'O', 'X'},
            new char[]{'O', 'X', 'O'},
            new char[]{'X', 'O', 'X'},
        };

        q130.solve(board);
    }
}
