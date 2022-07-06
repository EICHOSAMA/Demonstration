package per.eicho.demo.leetcode.q901_1000;

import java.util.PriorityQueue;

/**
 * <p>973. K Closest Points to Origin 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/k-closest-points-to-origin/">
 *   973. K Closest Points to Origin</a>
 */
public final class Q973 {
    public int[][] kClosest(int[][] points, int k) {
        // 1. 1 <= k <= points.length <= 10^4
        // 2. -10^4 < xi, yi < 10^4
        final int n = points.length;
        final PriorityQueue<Integer> heap = new PriorityQueue<>((idx1, idx2) -> {
            final int[] p1 = points[idx1];
            final int[] p2 = points[idx2];
            return distance(p1) - distance(p2);
        });
        for (int i = 0; i < n; i++) heap.add(i);

        final int[][] result = new int[k][];
        int p = 0;
        while (p < k) {
            result[p++] = points[heap.poll()];
        }
        return result;
    }

    private int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
