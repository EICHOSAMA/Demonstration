package per.eicho.demo.leetcode.q001_100;

/**
 * <p>37. Sudoku Solver 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sudoku-solver/">37. Sudoku Solver</a>
 */
public final class Q37 {

    private static final class Q37ProcessingContext {

        final static char UNUSED_MARK_CH = '.';
        final static int UNUSED_MARK_NUM = -1;

        final int[][] board;

        final int[][] rowMarks;
        final int[][] colMarks;
        final int[][][] boxMarks;

        Q37ProcessingContext(char[][] oldBoard) {
            this.board = new int[9][9];
            this.rowMarks = new int[9][9];
            this.colMarks = new int[9][9];
            this.boxMarks = new int[3][3][9];

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    final char ch = oldBoard[i][j]; 
                    if (ch == UNUSED_MARK_CH) {
                        board[i][j] = UNUSED_MARK_NUM;
                    } else {
                        final int num = ch - '0'; 
                        board[i][j] = num;
                        this.mark(i, j, num, true);
                    }
                }
            }
        }

        private void mark(int i, int j, int num, boolean used) {
            final int numIdx = num - 1;
            final int mark = used ? 1 : 0;

            rowMarks[i][numIdx] = mark;
            colMarks[j][numIdx] = mark;
            boxMarks[i / 3][j / 3][numIdx] = mark;
        }

        private boolean search(int x, int y) {
            if (x > 8) return true;
            //assert x <= 8 : "Already checked";

            if (/* x <= 8 && */ y > 8) return search(x + 1, 0); // 改行
            //assert y <= 8 : "Already checked";

            if (board[x][y] != UNUSED_MARK_NUM) return search(x, y + 1);
            
            for (int i = 0; i < 9; i++) {
                final int num = i + 1;
                
                if (colMarks[y][i] == 1) continue;
                if (rowMarks[x][i] == 1) continue;
                if (boxMarks[x / 3][y / 3][i] == 1) continue;

                // num can use
                mark(x, y, num, true);
                board[x][y] = num;

                if (search(x, y + 1)) {
                    return true;
                } else {
                    board[x][y] = UNUSED_MARK_NUM;
                    mark(x, y, num, false);    
                }
            }

            return false;
        }

        public int[][] fillBoard() {
            search(0, 0);
            return this.board;
        }
    }

    public void solveSudoku(char[][] board) {
        final Q37ProcessingContext context = new Q37ProcessingContext(board);
        final int[][] newBoard = context.fillBoard(); 

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = (char)(newBoard[i][j] + (int)'0');
            }
        }
    }

    public static void main(String[] args) {
        char[][] input = new char[][] {
            new char[]{'5','3','.','.','7','.','.','.','.'},
            new char[]{'6','.','.','1','9','5','.','.','.'},
            new char[]{'.','9','8','.','.','.','.','6','.'},
            new char[]{'8','.','.','.','6','.','.','.','3'},
            new char[]{'4','.','.','8','.','3','.','.','1'},
            new char[]{'7','.','.','.','2','.','.','.','6'},
            new char[]{'.','6','.','.','.','.','2','8','.'},
            new char[]{'.','.','.','4','1','9','.','.','5'},
            new char[]{'.','.','.','.','8','.','.','7','9'}
        };

        final Q37 q37 = new Q37();
        q37.solveSudoku(input);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(input[i][j]);
            }
            System.out.println();
        }
    }
}
