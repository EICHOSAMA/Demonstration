package per.eicho.demo.leetcode.q001_100;

/**
 * <p>11. Container With Most Water 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/container-with-most-water/">11. Container With Most Water</a>
 */
public final class Q11 {
    public int maxArea(int[] height) {
        // 1. n == height.length
        // 2. 2 <= n <= 10^5
        // 3. 0 <= height[i] <= 10^4        
        final int n = height.length;

        int l = 0, r = n - 1;
        int hL = height[l], hR = height[r];
        int result = 0;
        while (l != r) {
            result = Math.max(result, Math.min(hL, hR) * (r - l));
            if (hL < hR) {
                hL = height[++l];
            } else {
                hR = height[--r];
            }
        }
        return result;   
    }
}
