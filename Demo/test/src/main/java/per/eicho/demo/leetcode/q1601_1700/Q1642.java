package per.eicho.demo.leetcode.q1601_1700;

import java.util.PriorityQueue;

/**
 * <p>1642. Furthest Building You Can Reach 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/furthest-building-you-can-reach/">
 *   1642. Furthest Building You Can Reach</a>
 */
public final class Q1642 {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        // 1. 1 <= heights.length <= 10^5
        // 2. 1 <= heights[i] <= 10^6
        // 3. 0 <= bricks <= 10^9
        // 4. 0 <= ladders <= heights.length
        final int n = heights.length;
        int p = 0;
        int h = heights[p];
        final PriorityQueue<Integer> heap = new PriorityQueue<>((h1, h2) -> h2 - h1);
        while (p + 1 < n) {
            final int nextH = heights[++p];
            
            // case1: down
            if (nextH <= h) {
                h = nextH; // move
                continue;
            }

            // case2: up
            // nextH > h
            final int diff = nextH - h;
            if (bricks >= diff) {
                bricks -= diff;
                heap.offer(diff);
                h = nextH;
            } else if (ladders > 0) {
                bricks -= diff;
                heap.offer(diff);
                ladders--;
                bricks += heap.poll();
                h = nextH;
            } else {
                p--;
                break;
            }
        }
        return p;
    }
}
