package per.eicho.demo.leetcode.q001_100;

/**
 * <p>79. Word Search 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/word-search/">79. Word Search</a>
 */
public final class Q79 {
    final static char USED_MARK = '0';

    public boolean exist(char[][] board, String word) {
        /**
         * m == board.length
         * n = board[i].length
         * 1 <= m, n <= 6
         * 1 <= word.length <= 15
         * board and word consists of only lowercase and uppercase English letters.
         */

        final int m = board.length;
        final int n = board[0].length;

        final char firstLetter = word.charAt(0);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (firstLetter != board[i][j]) continue;
                if (search(i, j, 0, board, word) == true) return true;
            }
        }
        return false;
    }

    private boolean search(int x, int y, final int p, final char[][] board, final String word) {
        // 边界条件
        if (p + 1 == word.length()) return true;

        // 标记为已使用(mark as used)
        board[x][y] = USED_MARK; 
        final char target = word.charAt(p + 1);
        
        // search
        if (isValidCoordinate(x - 1, y, board, target)) {
            if (search(x - 1, y, p + 1, board, word) == true) {
                // 去除标记(mark as unused)
                board[x][y] = word.charAt(p);
                return true;
            }
        }

        if (isValidCoordinate(x + 1, y, board, target)) {
            if (search(x + 1, y, p + 1, board, word) == true) {
                // 去除标记(mark as unused)
                board[x][y] = word.charAt(p);
                return true;
            }
        }

        if (isValidCoordinate(x, y - 1, board, target)) {
            if (search(x, y - 1, p + 1, board, word) == true) {
                // 去除标记(mark as unused)
                board[x][y] = word.charAt(p);
                return true;
            }
        }
        if (isValidCoordinate(x, y + 1, board, target)) {
            if (search(x, y + 1, p + 1, board, word) == true) {
                // 去除标记(mark as unused)
                board[x][y] = word.charAt(p);
                return true;
            }
        }


        // 去除标记(mark as unused)
        board[x][y] = word.charAt(p);        
        return false;
    }

    private boolean isValidCoordinate(int x, int y, final char[][] board, final char target) {
        if (x < 0 || x >= board.length) return false;
        if (y < 0 || y >= board[0].length) return false;
        return board[x][y] == target;
    }
}
