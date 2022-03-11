package per.eicho.demo.leetcode.q1601_1700;

/**
 * <p>1672. Richest Customer Wealth 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/richest-customer-wealth/">1672. Richest Customer Wealth</a>
 */
public final class Q1672 {
    public int maximumWealth(int[][] accounts) {
        // 1. m == accounts.length
        // 2. n == accounts[i].length
        // 3. 1 <= m, n <= 50
        // 4. 1 <= accounts[i][j] <= 100
        final int m = accounts.length;
        final int n = accounts[0].length;
        int maximumWealth = 0;

        for (int i = 0; i < m; i++) {
            int wealth = 0;

            for (int j = 0; j < n; j++) {
                wealth += accounts[i][j];    
            }

            maximumWealth = Math.max(maximumWealth, wealth);
        }

        return maximumWealth;
    }
}
