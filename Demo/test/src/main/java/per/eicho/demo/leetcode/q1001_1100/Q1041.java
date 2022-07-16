package per.eicho.demo.leetcode.q1001_1100;

/**
 * <p>1041. Robot Bounded In Circle 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/robot-bounded-in-circle/">
 *   1041. Robot Bounded In Circle</a>
 */
public final class Q1041 {

    private static final int[][] directions = new int[][]{
        new int[]{0, 1},  // North ↓ R ↑ L
        new int[]{1, 0},  // East
        new int[]{0, -1}, // South
        new int[]{-1, 0}  // West
    };

    public boolean isRobotBounded(String instructions) {
        // 1. 1 <= instructions.length <= 100
        // 2. instructions[i] is 'G', 'L' or, 'R'.
        final int n = instructions.length();
        int[] infos = new int[]{0, 0, 0};
        for (int time = 0; time < 4; time++) {
            for (int i = 0; i < n; i++) {
                final char ch = instructions.charAt(i);

                if (ch == 'G') {
                    final int[] direction = directions[infos[2]];
                    infos[0] += direction[0];
                    infos[1] += direction[1];
                } else if (ch == 'L') {
                    infos[2] = (infos[2] + 3) % 4;
                } else {
                    // ch == 'R'
                    infos[2] = (infos[2] + 1) % 4;
                }
            }

            if (infos[0] == 0 && infos[1] == 0) return true;
        }
        return false;
    }
}
