package per.eicho.demo.leetcode.q1_100;

/**
 * <p>59. Spiral Matrix II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/spiral-matrix-ii/">59. Spiral Matrix II</a>
 */
final public class Q59 {

    private static class Coordinate {
        int x = 0;
        int y = 0;

        int tempX = 0;
        int tempY = 0;


        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private void tryMove(Direction direction) {
            tempX = x + direction.offset_x;
            tempY = y + direction.offset_y;
        }

        private void commit() {
            x = tempX;
            y = tempY;
        }
    }

    public enum Direction {
        RIGHT(0, 1),
        DOWN(1, 0),
        LEFT(0, -1),
        UP(-1, 0);

        private int offset_x;
        private int offset_y;

        private Direction(int offset_x, int offset_y) {
            this.offset_x = offset_x;
            this.offset_y = offset_y;
        }

        private Direction next() {
            switch (this) {
                case RIGHT:
                    return DOWN;
                case DOWN:
                    return LEFT;
                case LEFT: 
                    return UP;
                default:
                    return RIGHT;
            }

        }
    }

    public int[][] generateMatrix(int n) {
        // assert 1 <= n && n <= 20;
        
        // 1. generate matrix
        final int[][] matrix = new int[n][n];

        // 2. 
        Coordinate coordinate = new Coordinate(0, -1);
        Direction direction = Direction.RIGHT;
        for (int i = 1; i <= n * n; i++) {
            // try
            coordinate.tryMove(direction);

            //
            if (0 <= coordinate.tempX && coordinate.tempX < n &&
            0 <= coordinate.tempY && coordinate.tempY < n) {
                if (matrix[coordinate.tempX][coordinate.tempY] == 0) {
                    coordinate.commit();
                    matrix[coordinate.x][coordinate.y] = i;
                    continue;
                }
            }

            // change direciton and try again.
            direction = direction.next();
            i--;
        }

        return matrix;
    }
}
