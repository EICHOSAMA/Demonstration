package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>54. Spiral Matrix 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/spiral-matrix/">54. Spiral Matrix</a>
 */
public final class Q54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        // 1. m == matrix.length
        // 2. n == matrix[i].length
        // 3. 1 <= m, n <= 10
        // 4. -100 <= matrix[i][j] <= 100        
        int rows = matrix.length;
        if (0 == rows) return new ArrayList<>(0);
        int columns = matrix[0].length;

        List<Integer> result = new ArrayList<>(rows * columns);

        final int layers = (Math.min(rows, columns) + 1) / 2; // number of layer.
        for (int currentLayer = 0; currentLayer < layers ; currentLayer++) {
            int x, y;
            // 1. Direction: right. (currentLayer, j++)
            x = currentLayer; y = currentLayer; // (x, y) is the start point of current direction.
            int rightBound = columns - currentLayer; // exclusive.
            while (y < rightBound) result.add(matrix[x][y++]);

            // 2. Direction: bottom. (currentLayer++, j)
            x++; y--; // new start point.
            int bottomBound = rows - currentLayer; // exclusive.
            if (x == bottomBound) break;
            while (x < bottomBound) result.add(matrix[x++][y]);

            // 3. Direction: left. (currentLayer, j--)
            x--; y--; // new start point.
            int leftBound = currentLayer; // inclusive.
            if (y < leftBound) break;
            while (y >= leftBound) result.add(matrix[x][y--]);

            // 4. Direction: up. (currentLayer--, j)
            x--; y++; // new start point.
            int upBound = currentLayer; // exclusive.
            if (x == upBound) break;
            while (x > upBound) result.add(matrix[x--][y]);
        }

        return result; 
    }
}
