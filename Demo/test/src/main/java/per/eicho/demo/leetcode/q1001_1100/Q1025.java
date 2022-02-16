package per.eicho.demo.leetcode.q1001_1100;

/**
 * <p>1025. Divisor Game 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/divisor-game/">
 *   1025. Divisor Game</a>
 */
public final class Q1025 {
    public boolean divisorGame(int n) {
        // 1. 1 <= n <= 1000
        final boolean[] f = new boolean[n + 1];
        f[1] = false;
        for (int i = 1; i < n; i++) {
            
        }

        return f[n];
    }
}
