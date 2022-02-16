package per.eicho.demo.leetcode.q801_900;

/**
 * <p>812. Largest Triangle Area 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/largest-triangle-area/">812. Largest Triangle Area</a>
 * @see <a href="https://en.wikipedia.org/wiki/Shoelace_formula">Shoelace formula</a>
 */
public final class Q812 {
    public double largestTriangleArea(int[][] points) {
        // 1. 3 <= points.length <= 50
        // 2. -50 <= xi, yi <= 50
        // 3. All the given points are unique.
        final int n = points.length; 
        double result = 0.0;

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    result = Math.max(result, getArea(points[i], points[j], points[k]));
                }
            }
        }

        return result;
    }

    private double getArea(int[] p1, int[] p2, int[] p3) {
        double area = (getArea(p1, p2) + getArea(p2, p3) + getArea(p3, p1)) / 2.0;
        return Math.abs(area);
    }

    private int getArea(int[] p1, int[] p2) {
        return (p1[1] + p2[1]) * (p1[0] - p2[0]);
    }
}
