package per.eicho.demo.leetcode.q1401_1500;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>1496. Path Crossing 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/path-crossing/">
 *   1496. Path Crossing</a>
 */
public final class Q1496 {

    public boolean isPathCrossing(String path) {
        // 1. 1 <= path.length <= 10^4
        // 2. path[i] is either 'N', 'S', 'E', or 'W'.        
        final Set<Integer> marks = new HashSet<Integer>();
        final int n = path.length();
        
        int x = 0, y = 0;
        marks.add(hashCode(x, y));

        for (int i = 0; i < n; i++) {
            final char move = path.charAt(i);
            switch (move) {
                case 'N': x--; break;
                case 'S': x++; break;
                case 'W': y--; break;
                case 'E': y++; break;
            }
            final int hashValue = hashCode(x, y);
            if (!marks.add(hashValue)) return true;
        }

        return false;
    }

    private int hashCode(int x, int y) { return x * 20001 + y; }

    public static void main(String[] args) {
        Q1496 q1496 = new Q1496();
        System.out.println(q1496.isPathCrossing("SN")); // TRUE
        System.out.println(q1496.isPathCrossing("NES")); // FALSE
    }
}
