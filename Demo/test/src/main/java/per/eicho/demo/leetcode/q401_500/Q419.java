package per.eicho.demo.leetcode.q401_500;

/**
 * <p>419. Battleships in a Board 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/battleships-in-a-board/">
 *   419. Battleships in a Board</a>
 */
public final class Q419 {
    public int countBattleships(char[][] board) {
        // 1. m == board.length
        // 2. n == board[i].length
        // 3. 1 <= m, n <= 200
        // 4. board[i][j] is either '.' or 'X'.        
        final int m = board.length;
        final int n = board[0].length;
        
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') continue;
                if (i - 1 >= 0 && board[i - 1][j] == 'X') continue;
                if (j - 1 >= 0 && board[i][j - 1] == 'X') continue;
                count++;
            }
        }
        return count;
    }
}
