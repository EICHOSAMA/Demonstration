package per.eicho.demo.leetcode.q1201_1300;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>1222. Queens That Can Attack the King 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/queens-that-can-attack-the-king/">
 *   1222. Queens That Can Attack the King</a>
 */
public final class Q1222 {
    final int n = 8;
    List<List<Integer>> result;
    final int[][] matrix = new int[8][8];
    final int[][] directions = new int[][] {
        new int[]{-1, -1}, new int[]{-1, 0}, new int[]{-1, 1},
        new int[]{0 , -1},                   new int[]{0, 1},
        new int[]{1, -1} , new int[]{1, 0} , new int[]{1, 1}
    };

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        // 1. 1 <= queens.length <= 63
        // 2. queens[i].length == 2
        // 3. 0 <= queens[i][j] < 8
        // 4. king.length == 2
        // 5. 0 <= king[0], king[1] < 8
        // 6. At most one piece is allowed in a cell.
        result = new LinkedList<>();
        for (int[] queen : queens) {
            matrix[queen[0]][queen[1]] = 1;
        }

        for (int[] direction : directions) {
            search(king[0], king[1], direction);
        }

        return result;
    }

    private void search(int x, int y, int[] direction) {
        if (x < 0 || x >= n) return;
        if (y < 0 || y >= n) return;

        if (matrix[x][y] == 1) {
            result.add(Arrays.asList(x, y));
            return;
        }

        search(x + direction[0], y + direction[1], direction);
    }
}
