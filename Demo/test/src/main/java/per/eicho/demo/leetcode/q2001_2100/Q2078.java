package per.eicho.demo.leetcode.q2001_2100;

/**
 * <p>2078. Two Furthest Houses With Different Colors 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/two-furthest-houses-with-different-colors/">2078. Two Furthest Houses With Different Colors</a>
 */
public final class Q2078 {
    public int maxDistance(int[] colors) {
        // 1. n == colors.length
        // 2. 2 <= n <= 100
        // 3. 0 <= colors[i] <= 100
        // 4. Test data are generated such that at least two houses have different colors.
        int result = 0;
        for (int i = 0; i < colors.length - 1; i++) {
            final int colorI = colors[i];
            for (int j = colors.length - 1; j > i + result; j--) {
                final int colorJ = colors[j];
                if (colorI == colorJ) continue;
                result = Math.max(result, j - i);
            }
        }
        return result;
    }
}
