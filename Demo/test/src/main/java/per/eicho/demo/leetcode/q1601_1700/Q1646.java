package per.eicho.demo.leetcode.q1601_1700;

/**
 * <p>1646. Get Maximum in Generated Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/get-maximum-in-generated-array/">
 *   1646. Get Maximum in Generated Array</a>
 */
public final class Q1646 {
    public int getMaximumGenerated(int n) {
        // 1. 0 <= n <= 100        
        if (n <= 1) return n;
        final int[] f = new int[n + 1];
        int result = 1;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                f[i] = f[i / 2];
            } else {
                f[i] = f[i / 2] + f[(i / 2) + 1];
            }
            result = Math.max(result, f[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Q1646 q1646 = new Q1646();
        System.out.println(q1646.getMaximumGenerated(4));
    }
}
