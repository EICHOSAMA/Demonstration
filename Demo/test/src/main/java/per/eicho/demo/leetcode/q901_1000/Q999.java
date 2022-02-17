package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>999. Available Captures for Rook 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/available-captures-for-rook/">999. Available Captures for Rook</a>
 */
public final class Q999 {

    public int numRookCaptures(char[][] board) {
        // 1. board.length == 8
        // 2. board[i].length == 8
        // 3. board[i][j] is either 'R', '.', 'B', or 'p'
        // 4. There is exactly one cell with board[i][j] == 'R'        
        int x = -1, y = -1;

        search:
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') {
                    x = i;
                    y = j;
                    break search;
                }
            }
        }

        int count = 0;

        int i = x - 1, j = y;
        while (i >= 0) {
            char cell = board[i--][j];
            if (cell == '.') continue;
            if (cell == 'B') break;
            count++;
            break;
        }

        i = x + 1;
        while (i < 8) {
            char cell = board[i++][j];
            if (cell == '.') continue;
            if (cell == 'B') break;
            count++;
            break;
        }
        
        i = x;
        j = y - 1;
        while (j >= 0) {
            char cell = board[i][j--];
            if (cell == '.') continue;
            if (cell == 'B') break;
            count++;
            break;
        }

        j = y + 1;
        while (j < 8) {
            char cell = board[i][j++];
            if (cell == '.') continue;
            if (cell == 'B') break;
            count++;
            break;
        }

        return count;
    }
}
