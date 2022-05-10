package per.eicho.demo.leetcode.q301_400;

/**
 * <p>390. Elimination Game 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/elimination-game/">390. Elimination Game</a>
 */
public final class Q390 {
    public int lastRemaining(int n) {
        // 1. 1 <= n <= 10^9 
        if (n == 1) return 1;
        final int half = n / 2;
        final int nextLayer = lastRemaining(half);
        return (half - nextLayer + 1) * 2;
    }
}
