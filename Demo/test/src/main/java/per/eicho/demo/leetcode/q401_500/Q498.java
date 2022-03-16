package per.eicho.demo.leetcode.q401_500;

/**
 * <p>498. Diagonal Traverse 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/diagonal-traverse/">498. Diagonal Traverse</a>
 */
public final class Q498 {

    private static enum Direction {
        RIGHT_UP(-1, 1),
        LEFT_DOWN(1, - 1);

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int x;
        int y;

        void move(int[] coordinate) {
            coordinate[0] += x;
            coordinate[1] += y;
        }

        Direction reverseDirection() {
            switch (this) {
                case RIGHT_UP: return LEFT_DOWN;
                default: return RIGHT_UP;
            }
        }
    }

    public int[] findDiagonalOrder(int[][] mat) {
        // 1. m == mat.length
        // 2. n == mat[i].length
        // 3. 1 <= m, n <= 10^4
        // 4. 1 <= m * n <= 10^4
        // 5. -10^5 <= mat[i][j] <= 10^5
        final int m = mat.length;
        final int n = mat[0].length;
        final int size = m * n;
        final int[] result = new int[size];

        int[] coordinate = new int[]{0, 0};
        int p = 0;
        Direction direction = Direction.RIGHT_UP;
        while (p < size) {
            result[p++] = mat[coordinate[0]][coordinate[1]];

            direction.move(coordinate);

            // coorect coordinate
            if ((coordinate[0] < 0 || coordinate[0] >= m) || 
                (coordinate[1] < 0 || coordinate[1] >= n)) {
                direction = direction.reverseDirection();
                direction.move(coordinate); // redo (move back)
                
                if (direction == Direction.RIGHT_UP) {
                    // Direction.RIGHT_UP →↑
                    if (coordinate[0] + 1 < m) {
                        coordinate[0]++;
                    } else {
                        coordinate[1]++;
                    }
                } else {
                    // Direction.LEFT_DOWN ↓←                    
                    if (coordinate[1] + 1 < n) {
                        coordinate[1]++;
                    } else {
                        coordinate[0]++;
                    }
                }
            }
        }
        return result;
    }
}
