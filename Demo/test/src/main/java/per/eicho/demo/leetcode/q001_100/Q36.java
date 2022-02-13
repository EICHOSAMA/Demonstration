package per.eicho.demo.leetcode.q001_100;

/**
 * <p>36. Valid Sudoku 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/valid-sudoku/">
 *   36. Valid Sudoku</a>
 */
public final class Q36 {
    public static boolean isValidSudoku(char[][] board) {
        // 1. board.length == 9
        // 2. board[i].length == 9
        // 3. board[i][j] is a digit 1-9 or '.'.        
        char temp;
        // 1. check row.
        for (int i = 0; i < 9; i++) {
            boolean[] flags = new boolean[9];
            for (int j = 0; j < 9; j++) {
                temp = board[i][j];
                if (temp == '.') continue;
                if ((flags[temp - '1'] ^= true) == false) return false;
            }
        }

        // 2. check column.
        for (int i = 0; i < 9; i++) {
            boolean[] flags = new boolean[9];
            for (int j = 0; j < 9; j++) {
                temp = board[j][i];
                if (temp == '.') continue;
                if ((flags[temp - '1'] ^= true) == false) return false;
            }
        }

        // 3. check sub-box.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boolean[] flags = new boolean[9];
                for (int k = 0, offsetI = i * 3; k < 3; k++) {
                    for (int l = 0, offsetJ = j * 3; l < 3; l++) {
                        temp = board[offsetI + k][offsetJ + l];
                        if (temp == '.') continue;
                        if ((flags[temp - '1'] ^= true) == false) return false;
                    }
                }
            }
        }

        return true;
    }
}
