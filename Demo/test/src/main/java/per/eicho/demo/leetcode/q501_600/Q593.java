package per.eicho.demo.leetcode.q501_600;

/**
 * <p>593. Valid Square 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/valid-square/">
 *   593. Valid Square</a>
 */
public final class Q593 {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        // 1. p1.length == p2.length == p3.length == p4.length == 2
        // 2. -10^4 <= xi, yi <= 10^4        
        final int[] a = p1;

        int dp2 = getDistancePower2(a, p2);
        int dp3 = getDistancePower2(a, p3);
        int dp4 = getDistancePower2(a, p4);

        final int[] b;
        final int[] c;
        final int[] d;
        final int edgeP;
        final int diagonalP;
        if (dp2 == dp3) {
            if (dp4 != dp2 + dp3) return false;
            b = p2;
            c = p4;
            d = p3;
            edgeP = dp2;
            diagonalP = dp4;
        } else if (dp3 == dp4) {
            if (dp2 != dp3 + dp4) return false;
            b = p3;
            c = p2;
            d = p4;
            edgeP = dp3;
            diagonalP = dp2;
        } else if (dp2 == dp4) {
            if (dp3 != dp2 + dp4) return false;
            b = p2;
            c = p3;
            d = p4;
            edgeP = dp2;
            diagonalP = dp3;
        } else return false;

        return edgeP != 0 && getDistancePower2(c, b) == edgeP && getDistancePower2(c, d) == edgeP 
            && getDistancePower2(b, d) == diagonalP;
    }

    private int getDistancePower2(int[] p1, int p2[]) {
        final int a = p2[0] - p1[0];
        final int b = p2[1] - p1[1];
        return a * a + b * b;
    }
}
