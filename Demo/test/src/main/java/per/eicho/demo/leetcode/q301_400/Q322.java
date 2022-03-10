package per.eicho.demo.leetcode.q301_400;

import java.util.Arrays;

/**
 * <p>322. Coin Change 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/coin-change/">322. Coin Change</a>
 */
public final class Q322 {
    public int coinChange(int[] coins, int amount) {
        // 1. 1 <= coins.length <= 12
        // 2. 1 <= coins[i] <= 2^31 - 1
        // 3. 0 <= amount <= 10^4
        final int[] f = new int[amount + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for (int i = 0; i < amount; i++) {
            if (f[i] == Integer.MAX_VALUE) continue;
            for (int denomination : coins) {
                if (denomination - amount > -i) continue;
                f[i + denomination] = Math.min(f[i + denomination], f[i] + 1);
            }
        }
        return f[amount] == Integer.MAX_VALUE ? 0 : f[amount];
    }
}