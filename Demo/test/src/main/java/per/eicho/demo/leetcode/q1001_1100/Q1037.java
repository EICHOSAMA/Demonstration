package per.eicho.demo.leetcode.q1001_1100;

/**
 * <p>1037. Valid Boomerang 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/valid-boomerang/">1037. Valid Boomerang</a>
 * @see <a href="https://en.wikipedia.org/wiki/Shoelace_formula">Shoelace formula</a>
 */
public final class Q1037 {
    public boolean isBoomerang(int[][] points) {
        // 1. points.length == 3
        // 2. points[i].length == 2
        // 3. 0 <= xi, yi <= 100        
        int area = 0;
        for (int i = 0; i < 3; i++) {
            final int[] p1 = points[i];
            final int[] p2 = points[(i + 1) % 3];

            area += ((p1[0] + p2[0]) * (p1[1] - p2[1]));
        }

        return area != 0;
    }
}
