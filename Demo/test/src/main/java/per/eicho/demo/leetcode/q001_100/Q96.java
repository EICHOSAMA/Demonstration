package per.eicho.demo.leetcode.q001_100;

/**
 * <p>96. Unique Binary Search Trees 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/unique-binary-search-trees/">96. Unique Binary Search Trees</a>
 */
public final class Q96 {
    final int[] f = new int[20]; // 0-19
    
    public int numTrees(int n) {
        f[0] = 1;
        f[1] = 1;

        return dp(n);
    }

    public int dp(int n) {
        if (f[n] != 0) return f[n];

        int result = 0;
        final int sum = n - 1;
        for (int i = 0; i < n; i++) {
            result += dp(i) * dp(sum - i);
        }

        f[n] = result;
        return result;
    }
}
