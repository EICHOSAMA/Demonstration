package per.eicho.demo.leetcode.q001_100;

/**
 * <p>42. Trapping Rain Water 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/trapping-rain-water/">42. Trapping Rain Water</a>
 */
public final class Q42DPSolution {
    public int trap(int[] height) {
        // 1. n == height.length
        // 2. 1 <= n <= 2 * 10^4
        // 3. 0 <= height[i] <= 10^5        
        final int n = height.length;
        
        // 1. prepare left
        final int[] lHighest = new int[n];
        lHighest[0] = height[0];
        for (int i = 1; i < n; i++) {
            lHighest[i] = Math.max(height[i], lHighest[i - 1]);
        }

        // 2. prepare right
        final int[] rHighest = new int[n];
        rHighest[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rHighest[i] = Math.max(height[i], rHighest[i + 1]);
        }

        // 3. 
        int result = 0;
        for (int i = 1; i < n - 1; i++) {
            result += Math.min(lHighest[i], rHighest[i]) - height[i];
        }
        return result;
    }
}
