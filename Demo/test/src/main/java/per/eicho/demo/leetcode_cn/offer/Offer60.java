package per.eicho.demo.leetcode_cn.offer;

/**
 * <p>剑指 Offer 60. n个骰子的点数 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof/">
 *   剑指 Offer 60. n个骰子的点数</a>
 */
public final class Offer60 {
    private static final double SIX_ONE = 1.0d / 6.0d;
    private static final double[][] f = new double[12][67];

    static {
        for (int i = 1; i <= 6; i++) { f[1][i] = SIX_ONE; }
        for (int i = 1; i <= 10; i++) {
            for (int val = i * 1; val <= i * 6; val++) {
                final double p = f[i][val];
                for (int j = 1; j <= 6; j++) {
                    f[i + 1][val + j] += (p * SIX_ONE);
                }
            }
        }
    }

    public double[] dicesProbability(int n) {
        // 1 <= n <= 11
        final double[] row = f[n];
        final double[] result = new double[n * 5 + 1];
        System.arraycopy(row, n * 1, result, 0, result.length);
        return result;
    }
}
