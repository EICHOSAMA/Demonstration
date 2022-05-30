package per.eicho.demo.leetcode.q401_500;

import java.util.Random;

/**
 * <p>478. Generate Random Point in a Circle 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/generate-random-point-in-a-circle/">
 *   478. Generate Random Point in a Circle</a>
 */
@SuppressWarnings("unused")
public final class Q478 {

    private static final class Solution {

        final Random random;
        final double x;
        final double y;
        final double r;

        /**
         * initializes the object with the radius of the circle radius and the position of the center (x_center, y_center).
         * @param radius
         * @param x_center
         * @param y_center
         */
        public Solution(double radius, double x_center, double y_center) {
            // 1. 0 < radius <= 10^8
            // 2. -10^7 <= x_center, y_center <= 10^7
            random = new Random(System.currentTimeMillis());

            r = radius;
            x = x_center;
            y = y_center;
        }
        
        /**
         * returns a random point inside the circle. 
         * A point on the circumference of the circle is considered to be in the circle. 
         * The answer is returned as an array [x, y].
         * @return
         */
        public double[] randPoint() {
            // 3. At most 3 * 10^4 calls will be made to randPoint.
            double nx, ny;
            do {
                nx = x + random.nextDouble() * r * (random.nextBoolean() ? 1.0d : -1.0d);
                ny = y + random.nextDouble() * r * (random.nextBoolean() ? 1.0d : -1.0d);
            } while (!isInsideCycle(nx, ny));
            return new double[]{nx, ny};
        }

        private boolean isInsideCycle(double nx, double ny) {
            if ((nx - x) * (nx - x) + (ny - y) * (ny - y) <= r * r) return true;
            return false;
        }
    }
}
