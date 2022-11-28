package per.eicho.demo.leetcode.q001_100;

import java.util.PriorityQueue;

/**
 * <p>42. Trapping Rain Water 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/trapping-rain-water/">42. Trapping Rain Water</a>
 */
public final class Q42 {
    public int trap(int[] height) {
        // 1. n == height.length
        // 2. 1 <= n <= 2 * 10^4
        // 3. 0 <= height[i] <= 10^5        
        final int n = height.length;
        int result = 0;

        final PriorityQueue<Integer> heap = new PriorityQueue<>((i1, i2) -> {
            return Integer.compare(height[i1], height[i2]);
        });
        for (int i = 0; i < n; i++) {
            heap.add(i);
        }

        int l = heap.poll();
        int r = l;

        while (!heap.isEmpty()) {
            final int idx = heap.poll();
            final int h = height[idx];

            if (l < idx && idx < r) continue;
            if (idx < l) {
                result += calculate(height, idx, l, h);
                l = idx;
            } else {
                // r < idx
                result += calculate(height, r, idx, h);
                r = idx;  
            }
        }

        return result;
    }

    private int calculate(final int[] height, int l, int r, int h) {
        int result = 0;
        for (int i = l + 1; i < r; i++) {
            result += (h - height[i]);
        }
        return result;
    }
}
