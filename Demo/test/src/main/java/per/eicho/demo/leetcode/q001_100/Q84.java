package per.eicho.demo.leetcode.q001_100;

import java.util.Arrays;
import java.util.Stack;

/**
 * <p>84. Largest Rectangle in Histogram 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/largest-rectangle-in-histogram/">84. Largest Rectangle in Histogram</a>
 */
public final class Q84 {
    public int largestRectangleArea(int[] heights) {
        // 1. 1 <= heights.length <= 10^5
        // 2. 0 <= heights[i] <= 10^4
        final int n = heights.length;
        final Stack<Integer> stack = new Stack<>(); // store index info.
        final int[] l = new int[n];
        final int[] r = new int[n];
        Arrays.fill(r, n);

        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                r[stack.pop()] = i;
            }
            l[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        
        int result = 0;
        for (int i = 0; i < n; ++i) {
            result = Math.max(result, (r[i] - l[i] - 1) * heights[i]);
        }
        return result;
    }
}
