package per.eicho.demo.leetcode.q501_600;

/**
 * <p>529. Minesweeper 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minesweeper/">
 *   529. Minesweeper</a>
 */
public final class Q529 {

    int[][] neighbors = new int[][]{
        new int[]{-1, -1}, new int[]{-1, 0}, new int[]{-1, 1},
        new int[]{0, -1} ,                   new int[]{0, 1},
        new int[]{1, -1} , new int[]{1, 0} , new int[]{1, 1},
    };

    public char[][] updateBoard(char[][] board, int[] click) {
        // 1. m == board.length
        // 2. n == board[i].length
        // 3. 1 <= m, n <= 50
        // 4. board[i][j] is either 'M', 'E', 'B', or a digit from '1' to '8'.
        // 5. click.length == 2
        // 6. 0 <= clickr < m
        // 7. 0 <= clickc < n
        // 8. board[clickr][clickc] is either 'M' or 'E'.        
        final char val = board[click[0]][click[1]];

        // 1. If a mine 'M' is revealed, then the game is over. You should change it to 'X'.
        if (val == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        } 

        // val == 'E'
        reveal(board, click[0], click[1]);
        return board;
    }

    private void reveal(char[][] board, int i, int j) {
        final int count;
        System.out.println(i + ":" + j);
        if ((count = countAdjacentMines(board, i, j)) != 0) {
            // If an empty square 'E' with at least one adjacent mine is revealed, 
            // then change it to a digit ('1' to '8') representing the number of adjacent mines.
            board[i][j] = (char)('0' + count);
            return;
        }

        int ni, nj;
        board[i][j] = 'B';
        for (int[] neighbor : neighbors) {
            ni = i + neighbor[0];
            nj = j + neighbor[1];

            if (ni < 0 || ni >= board.length) continue;
            if (nj < 0 || nj >= board[0].length) continue; 

            // If an empty square 'E' with no adjacent mines is revealed, then change it to a revealed blank 'B' 
            // and all of its adjacent unrevealed squares should be revealed recursively.
            if (board[ni][nj] == 'E') reveal(board, ni, nj);
        }
    }

    private int countAdjacentMines(char[][] board, int i, int j) {
        int ni, nj;
        int count = 0;
        for (int[] neighbor : neighbors) {
            ni = i + neighbor[0];
            nj = j + neighbor[1];

            if (ni < 0 || ni >= board.length) continue;
            if (nj < 0 || nj >= board[0].length) continue;

            // 'M' represents an unrevealed mine,
            if (board[ni][nj] == 'M') count++;
        }
        return count;
    }
}
