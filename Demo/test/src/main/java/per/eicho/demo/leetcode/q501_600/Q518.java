package per.eicho.demo.leetcode.q501_600;

/**
 * <p>518. Coin Change 2 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/coin-change-2/">518. Coin Change 2</a>
 */
public final class Q518 {
    public int change(int amount, int[] coins) {
        // 1. 1 <= coins.length <= 300
        // 2. 1 <= coins[i] <= 5000
        // 3. All the values of coins are unique.
        // 4. 0 <= amount <= 5000        
        final int[] f = new int[amount + 1];
        f[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                f[i] += f[i - coin];
            }
        }
        return f[amount];
    }
}
