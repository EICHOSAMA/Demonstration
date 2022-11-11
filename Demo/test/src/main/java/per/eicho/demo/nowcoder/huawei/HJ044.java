package per.eicho.demo.nowcoder.huawei;

import java.util.Scanner;

public final class HJ044 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int p = -1;
        int[][] grid = null;
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            if (p >= 0) {
                final int x = p / 9;
                final int y = p % 9;
                final int num = in.nextInt();
                grid[x][y] = num;
                if (++p == 81) {
                    processInput(grid);
                    p = -1;
                }
            } else {
                p++;
                grid = new int[9][9];
            }
        }

        in.close();
    }

    private static void processInput(int[][] grid) {
        // 输入描述：包含已知数字的9X9盘面数组[空缺位以数字0表示]
        // 输出描述：完整的9X9盘面数组
        final Sudoku sudoku = new Sudoku(grid);
        sudoku.solve().print();
    }

    private static final class Sudoku {
        final int[][] grid;
        int zeroCount;
        final boolean[][] rowMarks = new boolean[9][10]; // 0-not-used
        final boolean[][] colMarks = new boolean[9][10]; // 0-not-used
        final boolean[][][] rectMarks = new boolean[3][3][10]; // 0-not-used

        Sudoku(int[][] grid) {
            this.grid = grid;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    final int num = grid[i][j];
                    if (num == 0) {
                        zeroCount++;
                    } else {
                        // assert 1 <= num && num <= 9;
                        rowMarks[i][num] = true;
                        colMarks[j][num] = true;
                        rectMarks[i / 3][j / 3][num] = true;
                    }
                }
            }
        }

        public Sudoku solve() {
            outer:
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (grid[i][j] != 0) continue;
                    search(i, j, zeroCount);
                    break outer;
                }
            }
            return this;
        }

        private boolean search(int x, int y, int zeroCount) {
            if (zeroCount == 0) return true;

            boolean[] rowMark = rowMarks[x];
            boolean[] colMark = colMarks[y];
            boolean[] rectMark = rectMarks[x / 3][y / 3];

            for (int num = 1; num <= 9; num++) {
                if (rowMark[num] || colMark[num] || rectMark[num]) continue;
                
                // 维护相关信息：使用
                grid[x][y] = num;
                rowMark[num] = true;
                colMark[num] = true;
                rectMark[num] = true;
                // 搜索
                if (zeroCount - 1 == 0) return true;
                outer:
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (grid[i][j] != 0) continue;
                        if (search(i, j, zeroCount - 1)) return true;
                        break outer;
                    }
                }

                // 维护相关信息：回溯
                grid[x][y] = 0;
                rowMark[num] = false;
                colMark[num] = false;
                rectMark[num] = false;                
            }

            return false;
        }

        public void print() {
            final int n = 9;
            for (int i = 0; i < n;) {
                for (int j = 0; j < n; ) {
                    System.out.print(grid[i][j]);
                    if (++j < n) System.out.print(' ');
                }
                if (++i < n) System.out.println();
            }
        }
    }
}
