package per.eicho.demo.leetcode.q101_200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>149. Max Points on a Line 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/max-points-on-a-line/">149. Max Points on a Line</a>
 */
public final class Q149 {

    /**
     * ax + by + c = 0;
     */
    private final static class Line {

        /**
         * a >= 0;
         */
        final int a;
        final int b;
        final int c;

        Line(Coordinate p1, Coordinate p2) {
            final int x1 = p1.x;
            final int y1 = p1.y;
            final int x2 = p2.x;
            final int y2 = p2.y;

            int tempA = y2 - y1;
            int tempB = x1 - x2;
            int tempC = y1 * (x2 - x1) - x1 * (y2 - y1);

            int gcdAB = gcd(tempA, tempB);
            int gcdBC = gcd(tempB, tempC);
            int gcdABC = gcd(gcdAB, gcdBC);

            tempA /= gcdABC;
            tempB /= gcdABC;
            tempC /= gcdABC;
            if (tempA < 0) {
                tempA *= -1;
                tempB *= -1;
                tempC *= -1;
            }

            a = tempA;
            b = tempB;
            c = tempC;
            System.out.println(a + ":" + b + ":" + c);
        }

        int gcd(int a, int b) {
            int temp;
            while (b != 0) {
                temp = a % b;
                a = b;
                b = temp;
            }
            return a;
        }

        boolean calcualted = false;
        int hashCode = 0;
        @Override
        public int hashCode() {
            if (calcualted) return hashCode;
            calcualted = true;
            return hashCode = ("" + a + b + c).hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            final Line l2 = (Line)obj;
            if (this.hashCode() != l2.hashCode()) return false;

            return this.a == l2.a && this.b == l2.b && this.c == l2.c;
        }
    }

    private final static class Coordinate {
        final int x;
        final int y;

        Coordinate(int[] p) {
            x = p[0];
            y = p[1];
        }

        boolean calcualted = false;
        int hashCode = 0;
        @Override
        public int hashCode() {
            if (calcualted) return hashCode;
            calcualted = true;
            return hashCode = (x + "." + y).hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            final Coordinate c2 = (Coordinate)obj;
            if (this.hashCode() != c2.hashCode()) return false;
            return this.x == c2.x && this.y == c2.y;
        }
    }

    public int maxPoints(int[][] points) {
        // 1. 1 <= points.length <= 300
        // 2. points[i].length == 2
        // 3. -10^4 <= xi, yi <= 10^4
        // 4. All the points are unique.        
        final int n = points.length;
        if (n == 1) return 1;

        final Map<Line, Set<Coordinate>> map = new HashMap<>();

        final List<Coordinate> newPoints = new ArrayList<>(n);
        for (int[] point : points) {
            newPoints.add(new Coordinate(point));
        }

        for (int i = 0; i < n - 1; i++) {
            final Coordinate p1 = newPoints.get(i);
            
            for (int j = i + 1; j < n; j++) {
                final Coordinate p2 = newPoints.get(j);
                
                final Line line = new Line(p1, p2);
                if (map.containsKey(line)) {
                    final Set<Coordinate> set = map.get(line);
                    set.add(p1);
                    set.add(p2);
                } else {
                    final Set<Coordinate> set = new HashSet<>();
                    set.add(p1);
                    set.add(p2);
                    map.put(line, set);
                }
            }
        }

        int result = 0;
        for (Set<Coordinate> ps : map.values()) {
            result = Math.max(result, ps.size());
        }
        return result;
    }

    public static void main(String[] args) {
        Q149 q149 = new Q149();
        int[][] input = new int[][] {
            new int[]{1, 1}, 
            new int[]{2, 2},
            new int[]{3, 3}
        };
        System.out.println(q149.maxPoints(input));
    }
}
