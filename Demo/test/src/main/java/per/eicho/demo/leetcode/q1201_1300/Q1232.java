package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1232. Check If It Is a Straight Line 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/check-if-it-is-a-straight-line/">
 *   1232. Check If It Is a Straight Line</a>
 */
public final class Q1232 {
    public boolean checkStraightLine(int[][] coordinates) {
        // 1. 2 <= coordinates.length <= 1000
        // 2. coordinates[i].length == 2
        // 3. -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
        // 4. coordinates contains no duplicate point.        
        final int n = coordinates.length;
        if (n == 2) return true;


        final int[] start = coordinates[0];
        int a = start[0] - coordinates[1][0];
        int b = coordinates[1][1] - start[1];


        for (int i = 2; i < n; i++) {
            final int[] coordinate = coordinates[i];

            final int na = start[0] - coordinate[0];
            final int nb = coordinate[1] - start[1];

            if (na * b != nb * a) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Q1232 q1232 = new Q1232();
        System.out.println(q1232.checkStraightLine(new int[][]{new int[]{1,1}, new int[]{2,2}, new int[]{2,1}, new int[]{3,2}}));
    }
}
