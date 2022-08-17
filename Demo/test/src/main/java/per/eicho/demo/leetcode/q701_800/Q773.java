package per.eicho.demo.leetcode.q701_800;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * <p>773. Sliding Puzzle 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sliding-puzzle/">
 *   773. Sliding Puzzle</a>
 */
public final class Q773 {

    private static final int target = 123456;
    private static final int[][] base = new int[][]{
        new int[]{100_000, 10_000, 1_000},
        new int[]{100, 10, 1}
    };

    private static final int[][] directions = new int[][]{
        new int[]{-1, 0}, new int[]{1, 0},
        new int[]{0, -1}, new int[]{0, 1}
    };

    public int slidingPuzzle(int[][] board) {
        // 1. board.length == 2
        // 2. board[i].length == 3
        // 3. 0 <= board[i][j] <= 5
        // 4. Each value board[i][j] is unique.
        final Map<Integer, Integer> f = new HashMap<>();
        for (int[] row : board) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] != 0) continue;
                row[i] = 6;
                break;
            }
        }
        int status = getStatusID(board);
        f.put(status, 0);

        final Queue<Integer> queue = new LinkedList<>();
        queue.offer(status);
        while (!queue.isEmpty() && !f.containsKey(target)) {
            final Integer iStatus = queue.poll();
            final int val = f.get(iStatus);
            
            fill(board, iStatus);

            Outer:
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 6) {
                        for (int[] direction : directions) {
                            final int ni = i + direction[0];
                            if (ni < 0 || ni >= 2) continue;
                            final int nj = j + direction[1];
                            if (nj < 0 || nj >= 3) continue;

                            swap(board, i, j, ni, nj);
                            final int nextStatus = getStatusID(board);
                            swap(board, i, j, ni, nj);
                            if (f.containsKey(nextStatus)) continue;
                            f.put(nextStatus, val + 1);
                            queue.offer(nextStatus);
                        }
                        break Outer;
                    }
                }
            }
        }

        return f.containsKey(target) ? f.get(target) : -1;
    }

    private void fill(int[][] board, int status) {
        for (int i = 1; i >= 0; i--) {
            int num = status % 1000;
            for (int j = 2; j >= 0; j--) {
                board[i][j] = num % 10;
                num /= 10;
            }
            status /= 1000;
        }
    }

    private void swap(int[][] board, int i, int j, int ni, int nj) {
        final int temp = board[i][j];
        board[i][j] = board[ni][nj];
        board[ni][nj] = temp;
    }

    private int getStatusID(int[][] board) {
        int id = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                id += board[i][j] * base[i][j];
            }
        }
        return id;
    }
}
