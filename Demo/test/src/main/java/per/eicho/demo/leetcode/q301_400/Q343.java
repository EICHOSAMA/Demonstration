package per.eicho.demo.leetcode.q301_400;

/**
 * <p>343. Integer Break 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/integer-break/">
 *   343. Integer Break</a>
 */
public final class Q343 {
    public int integerBreak(int n) {
        // 1. 2 <= n <= 58
        final int[] f = new int[n + 1];
        f[1] = 1;
        f[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                f[i] = Math.max(f[i], Math.max(f[j], j) * Math.max(f[i - j], i - j));
            }
        } 

        return f[n];
    }

    public static void main(String[] args) {
        Q343 q343 = new Q343();
        System.out.println(q343.integerBreak(3));
        System.out.println(q343.integerBreak(4));
        System.out.println(q343.integerBreak(5));
        System.out.println(q343.integerBreak(10));
        
        
    }
}
